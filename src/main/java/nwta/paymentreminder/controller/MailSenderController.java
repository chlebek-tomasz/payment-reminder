package nwta.paymentreminder.controller;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.service.AuthService;
import nwta.paymentreminder.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
@AllArgsConstructor
public class MailSenderController {

    private final MailService service;
    private final AuthService authService;

}
