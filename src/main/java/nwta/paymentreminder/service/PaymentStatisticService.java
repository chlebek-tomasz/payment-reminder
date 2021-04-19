package nwta.paymentreminder.service;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.dto.PaymentDTO;
import nwta.paymentreminder.dto.PaymentStatisticDTO;
import nwta.paymentreminder.enums.PaymentStatus;
import nwta.paymentreminder.exception.ResourceForbiddenException;
import nwta.paymentreminder.model.Payment;
import nwta.paymentreminder.repository.PaymentRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentStatisticService {

    private final PaymentRepository repository;
    private final AuthService authService;

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private List<PaymentDTO> paymentDTOS;

    public PaymentStatisticDTO getUpcomingPaymentsStatisticToTheEndOfMonth(Long userId) {
        if (authService.getCurrentUser().getId() == userId) {
            LocalDate now = LocalDate.now();
            LocalDate dueTo = now.with(TemporalAdjusters.lastDayOfMonth());
            return getPaymentStatisticSinceDueTo(userId, now, dueTo, null, PaymentStatus.IS_WAITING);
        } else throw new ResourceForbiddenException();
    }

    public PaymentStatisticDTO getPaymentsStatisticSinceTheBeginningOfMonthToNow(Long userId) {
        if (authService.getCurrentUser().getId() == userId) {
            LocalDate now = LocalDate.now();
            LocalDate since = now.with(TemporalAdjusters.firstDayOfMonth());
            return getPaymentStatisticSinceDueTo(userId, since, now, null, PaymentStatus.PAID);
        } else throw new ResourceForbiddenException();
    }

    public PaymentStatisticDTO getPaymentStatisticQueryParams(Long userId, LocalDate since, LocalDate dueTo, Long categoryId) {
        if (authService.getCurrentUser().getId() == userId) {
            return getPaymentStatisticSinceDueTo(userId, since, dueTo, categoryId, PaymentStatus.PAID);
        } else throw new ResourceForbiddenException();
    }

    private PaymentStatisticDTO getPaymentStatisticSinceDueTo(Long userId, LocalDate since, LocalDate dueTo, Long categoryId, PaymentStatus status) {
        double summaryAmount = 0;
        int paymentCounts = 0;
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        List<Payment> payments;
        if (categoryId != null)
            payments = repository.findByUserIdAndCategoryIdAndStatusAndDueToBetween(userId, categoryId, status, since, dueTo);
        else payments = repository.findByUserIdAndStatusAndDueToBetween(userId, status, since, dueTo);
        for (Payment payment : payments) {
            summaryAmount += payment.getAmount();
            paymentCounts += 1;
            paymentDTOS.add(PaymentService.buildPaymentDTO(payment));
        }
        return PaymentStatisticDTO.builder()
                .since(since.format(fmt))
                .dueTo(dueTo.format(fmt))
                .paymentCounts(paymentCounts)
                .summaryAmount(summaryAmount)
                .paymentDTOs(paymentDTOS)
                .build();
    }
}
