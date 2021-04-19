package nwta.paymentreminder.controller;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.dto.PaymentStatisticDTO;
import nwta.paymentreminder.service.PaymentStatisticService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/payments/statistics")
@AllArgsConstructor
public class PaymentStatisticController {

    private final PaymentStatisticService service;

    @GetMapping("/users/{id}/upcoming")
    public ResponseEntity<PaymentStatisticDTO> getUpcomingPaymentsStatisticToTheEndOfMonth(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getUpcomingPaymentsStatisticToTheEndOfMonth(id), HttpStatus.OK);
    }

    @GetMapping("/users/{id}/since-beginning-month")
    public ResponseEntity<PaymentStatisticDTO> getPaymentsStatisticSinceTheBeginningOfMonthToNow(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getPaymentsStatisticSinceTheBeginningOfMonthToNow(id), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<PaymentStatisticDTO> getPaymentStatisticQueryParams(@PathVariable("id") Long id,
                                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("since") LocalDate since,
                                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("dueTo") LocalDate dueTo,
                                                                              @RequestParam(value = "categoryId", required = false) Long categoryId) {
        return new ResponseEntity<>(service.getPaymentStatisticQueryParams(id, since, dueTo, categoryId), HttpStatus.OK);
    }

}
