package nwta.paymentreminder.scheduler;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.enums.PaymentStatus;
import nwta.paymentreminder.model.Payment;
import nwta.paymentreminder.repository.PaymentRepository;
import nwta.paymentreminder.service.MailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class PaymentScheduler {

    private final MailService mailService;
    private final PaymentRepository paymentRepository;

    @Scheduled(cron = "0 30 0 * * *")
    public void changePaymentStatusToExpiredScheduled() {
        changePaymentStatusToExpired();
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void sendEmailAboutPaymentExpiredFirstScheduled() {
        sendEmailAboutPaymentExpired();
    }

    @Scheduled(cron = "0 0 15 * * *")
    public void sendEmailAboutPaymentExpiredSecondScheduled() {
        sendEmailAboutPaymentExpired();
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void sendReminder1DayBeforeScheduled() {
        sendReminderNumberOfDaysBefore(1);
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void sendReminder3DaysBeforeScheduled() {
        sendReminderNumberOfDaysBefore(3);
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void sendReminder7DaysBeforeScheduled() {
        sendReminderNumberOfDaysBefore(7);
    }

    private void sendEmailAboutPaymentExpired() {
        List<Payment> payments = paymentRepository.findByStatus(PaymentStatus.EXPIRED);
        for (Payment p : payments) {
            mailService.sendMail(p.getUser().getEmail(),
                    "Przegapiłeś opłatę - " + p.getTitle(),
                    "<b>Przegapiłeś opłatę:</b><br />" +
                            "<b>Tytuł: </b>" + p.getTitle() + "<br />" +
                            "<b>Odbiorca: </b>" + p.getRecipient() + "<br />" +
                            "<b>Numer konta: </b>" + p.getRecipientAccountNumber() + "<br />" +
                            "<b>Kwota: </b>" + p.getAmount() + "<br />" +
                            "<b>Termin płatności: </b>" + p.getDueTo().toString() + "<br />", true);
        }
    }

    private void changePaymentStatusToExpired() {
        List<Payment> payments = paymentRepository.findByDueToAndStatus(LocalDate.now().minusDays(1), PaymentStatus.IS_WAITING);
        for (Payment p : payments) {
            p.setStatus(PaymentStatus.EXPIRED);
            paymentRepository.save(p);
        }
    }

    private void sendReminderNumberOfDaysBefore(int day) {
        List<Payment> payments = paymentRepository.findByDueToAndStatus(LocalDate.now().plusDays(day), PaymentStatus.IS_WAITING);
        String text = day == 1 ? "Został 1 dzień" : "Zostało " + day + " dni";
        for (Payment p : payments) {
            mailService.sendMail(p.getUser().getEmail(),
                    "Przypomnienie opłaty (" + text + ") - " + p.getTitle(),
                    "<b>Do opłaty:</b><br />" +
                            "<b>Tytuł: </b>" + p.getTitle() + "<br />" +
                            "<b>Odbiorca: </b>" + p.getRecipient() + "<br />" +
                            "<b>Numer konta: </b>" + p.getRecipientAccountNumber() + "<br />" +
                            "<b>Kwota: </b>" + p.getAmount() + "<br />" +
                            "<b>Termin płatności: </b>" + p.getDueTo().toString() + "<br />", true);
        }
    }
}
