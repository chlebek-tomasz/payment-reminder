package nwta.paymentreminder.scheduler;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.repository.PaymentRepository;
import nwta.paymentreminder.service.MailService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentScheduler {

    private final MailService service;
    private final PaymentRepository paymentRepository;
}
