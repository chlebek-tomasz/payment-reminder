package nwta.paymentreminder.repository;

import nwta.paymentreminder.model.Payment;
import nwta.paymentreminder.model.PaymentCategory;
import nwta.paymentreminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId);
    List<Payment> findByCategoryId(Long categoryId);
}
