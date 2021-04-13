package nwta.paymentreminder.service;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.dto.PaymentDTO;
import nwta.paymentreminder.enums.PaymentStatus;
import nwta.paymentreminder.exception.ResourceForbiddenException;
import nwta.paymentreminder.exception.ResourceNotFoundException;
import nwta.paymentreminder.model.Payment;
import nwta.paymentreminder.model.PaymentCategory;
import nwta.paymentreminder.model.User;
import nwta.paymentreminder.model.payload.PaymentRequest;
import nwta.paymentreminder.repository.PaymentCategoryRepository;
import nwta.paymentreminder.repository.PaymentRepository;
import nwta.paymentreminder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentCategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final AuthService service;

    public PaymentDTO addPayment(PaymentRequest request) throws ParseException {
        Payment payment = paymentRepository.save(buildPayment(request));
        return buildPaymentDTO(payment);
    }

    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(()-> {
            throw new ResourceNotFoundException();
        });
        if (payment.getUser().getId() != service.getCurrentUser().getId())
            throw new ResourceForbiddenException();
        return buildPaymentDTO(payment);
    }

    public List<PaymentDTO> getUserPayments(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        if (service.getCurrentUser().getId() != user.getId()) {
            throw new ResourceForbiddenException();
        }
        List<Payment> payments = paymentRepository.findByUserId(userId);
        return payments.stream()
                        .map(payment -> buildPaymentDTO(payment))
                        .collect(Collectors.toList());
    }

    public List<PaymentDTO> getUserPaymentsByCategoryId(Long id, Long categoryId) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        if (service.getCurrentUser().getId() != user.getId()) {
            throw new ResourceForbiddenException();
        }
        PaymentCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        List<Payment> payments = paymentRepository.findByCategoryId(category.getId());
        return payments.stream()
                        .map(payment -> buildPaymentDTO(payment))
                        .collect(Collectors.toList());
    }

    public PaymentDTO editPayment(Long id, PaymentRequest request) throws ParseException {
        Payment payment = paymentRepository.findById(id).orElseThrow(()-> {
            throw new ResourceNotFoundException();
        });
        if (payment.getUser().getId() != service.getCurrentUser().getId())
            throw new ResourceForbiddenException();
        Payment newPayment = buildPayment(request);
        newPayment.setId(payment.getId());
        newPayment.setStatus(payment.getStatus());
        return buildPaymentDTO(paymentRepository.save(newPayment));
    }

    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(()-> {
            throw new ResourceNotFoundException();
        });
        if (payment.getUser().getId() != service.getCurrentUser().getId())
            throw new ResourceForbiddenException();
        paymentRepository.delete(payment);
    }

    private Payment buildPayment(PaymentRequest request) throws ParseException {
        PaymentCategory category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return Payment.builder()
                .title(request.getTitle())
                .recipient(request.getRecipient())
                .amount(request.getAmount())
                .recipientAccountNumber(request.getRecipientAccountNumber())
                .dueTo(LocalDate.parse(request.getDueTo(), fmt))
                .status(PaymentStatus.IS_WAITING)
                .category(category)
                .periodicity(request.getPeriodicity())
                .user(service.getCurrentUser())
                .build();
    }

    private PaymentDTO buildPaymentDTO(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .dueTo(payment.getDueTo())
                .recipient(payment.getRecipient())
                .category(payment.getCategory())
                .status(payment.getStatus())
                .recipientAccountNumber(payment.getRecipientAccountNumber())
                .title(payment.getTitle())
                .build();
    }
}
