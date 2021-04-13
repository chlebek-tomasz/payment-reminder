package nwta.paymentreminder.repository;

import nwta.paymentreminder.model.PaymentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCategoryRepository extends JpaRepository<PaymentCategory, Long> {
}
