package nwta.paymentreminder.service;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.exception.ResourceForbiddenException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender javaMailSender;

    @Async
    public void sendMail(String to,
                         String subject,
                         String text,
                         boolean isHtmlContent) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, isHtmlContent);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new ResourceForbiddenException();
        }
    }
}
