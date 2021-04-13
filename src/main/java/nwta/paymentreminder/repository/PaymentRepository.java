package nwta.paymentreminder.repository;

import nwta.paymentreminder.enums.PaymentStatus;
import nwta.paymentreminder.model.Payment;
import nwta.paymentreminder.model.PaymentCategory;
import nwta.paymentreminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserIdAndStatusIn(Long userId, List<PaymentStatus> statuses);
    List<Payment> findByCategoryIdAndStatusIn(Long categoryId, List<PaymentStatus> statuses);
}
