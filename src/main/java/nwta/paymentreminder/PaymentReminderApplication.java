package nwta.paymentreminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PaymentReminderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentReminderApplication.class, args);
    }

}
