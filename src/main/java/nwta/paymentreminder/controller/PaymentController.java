package nwta.paymentreminder.controller;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.dto.PaymentDTO;
import nwta.paymentreminder.model.payload.PaymentRequest;
import nwta.paymentreminder.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getPaymentById(id), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<PaymentDTO>> getUserPayments(@PathVariable("id") Long id,
                                                            @RequestParam(name = "categoryId", required = false) Long categoryId) {
        if (categoryId != null)
            return new ResponseEntity<>(service.getUserPaymentsByCategoryId(id, categoryId, false), HttpStatus.OK);
        else return new ResponseEntity<>(service.getUserPayments(id, false), HttpStatus.OK);
    }

    @GetMapping("/users/{id}/history")
    public ResponseEntity<List<PaymentDTO>> getUserPaymentsHistory(@PathVariable("id") Long id,
                                                                   @RequestParam(name = "categoryId", required = false) Long categoryId) {
        if (categoryId != null)
            return new ResponseEntity<>(service.getUserPaymentsByCategoryId(id, categoryId, true), HttpStatus.OK);
        else return new ResponseEntity<>(service.getUserPayments(id, true), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PaymentDTO> addPayment(@RequestBody PaymentRequest request) throws ParseException {
        return new ResponseEntity<>(service.addPayment(request), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> editPayment(@PathVariable("id") Long id,
                                                  @RequestBody PaymentRequest request) throws ParseException {
        return new ResponseEntity<>(service.editPayment(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable("id") Long id) {
        service.deletePayment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
