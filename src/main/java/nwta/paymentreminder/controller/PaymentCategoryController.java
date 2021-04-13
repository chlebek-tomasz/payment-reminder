package nwta.paymentreminder.controller;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.model.PaymentCategory;
import nwta.paymentreminder.service.PaymentCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments-categories")
@AllArgsConstructor
public class PaymentCategoryController {

    private final PaymentCategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentCategory> getPaymentCategoryById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getPaymentCategoryById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PaymentCategory>> getPaymentCategories() {
        return new ResponseEntity<>(service.getPaymentCategories(), HttpStatus.OK);
    }

}
