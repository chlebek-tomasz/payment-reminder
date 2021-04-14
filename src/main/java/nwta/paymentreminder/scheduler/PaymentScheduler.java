package nwta.paymentreminder.scheduler;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.enums.PaymentStatus;
import nwta.paymentreminder.model.Payment;
import nwta.paymentreminder.repository.PaymentRepository;
import nwta.paymentreminder.service.MailService;
import nwta.paymentreminder.service.PaymentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PaymentScheduler {

    private final MailService mailService;
    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;

    @Scheduled(cron = "0 30 0 * * *")
    public void changePaymentStatusToExpired() {
        List<Payment> payments = paymentRepository.findByDueToAndStatus(LocalDate.now().minusDays(1), PaymentStatus.IS_WAITING);
        for (Payment p : payments) {
            p.setStatus(PaymentStatus.EXPIRED);
            paymentRepository.save(p);
        }
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void sendEmailAboutPaymentExpiredFirst() {
        sendEmailAboutPaymentExpired();
    }

    @Scheduled(cron = "0 0 15 * * *")
    public void sendEmailAboutPaymentExpiredSecond() {
        sendEmailAboutPaymentExpired();
    }

    private void sendEmailAboutPaymentExpired() {
        List<Payment> payments = paymentRepository.findByStatus(PaymentStatus.EXPIRED);
        for (Payment p : payments) {
            mailService.sendMail(p.getUser().getEmail(),
                    "Przegapiłeś opłatę - " + p.getTitle(),
                    "<b>Przegapiłeś opłatę:</b><br />" +
                            "<b>Tytuł: </b>" + p.getTitle() + "<br />" +
                            "<b>Odbiorca: </b>" + p.getRecipient() + "<br />" +
                            "<b>Numer konta: <b>" + p.getRecipientAccountNumber() + "<br />" +
                            "<b>Kwota: </b>" + p.getAmount() + "<br />" +
                            "<b>Termin płatności: </b>" + p.getDueTo().toString() + "<br />", true);
        }
    }
}
