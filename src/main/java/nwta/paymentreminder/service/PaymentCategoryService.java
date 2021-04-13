package nwta.paymentreminder.service;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.exception.ResourceNotFoundException;
import nwta.paymentreminder.model.PaymentCategory;
import nwta.paymentreminder.repository.PaymentCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PaymentCategoryService {

    private final PaymentCategoryRepository repository;

    public PaymentCategory getPaymentCategoryById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
    }

    public List<PaymentCategory> getPaymentCategories() {
        return repository.findAll();
    }
}
