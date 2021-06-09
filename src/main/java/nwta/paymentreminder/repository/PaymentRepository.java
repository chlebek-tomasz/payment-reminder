package nwta.paymentreminder.repository;

import nwta.paymentreminder.enums.PaymentStatus;
import nwta.paymentreminder.model.Payment;
import nwta.paymentreminder.model.PaymentCategory;
import nwta.paymentreminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserIdAndStatusIn(Long userId, List<PaymentStatus> statuses);
    List<Payment> findByCategoryIdAndStatusInAndUserId(Long categoryId, List<PaymentStatus> statuses, Long userId);
    List<Payment> findByDueToAndStatus(LocalDate date, PaymentStatus status);
    List<Payment> findByStatus(PaymentStatus status);
    Optional<Payment> findFirstByUserIdAndStatusOrderByDueTo(Long userId, PaymentStatus status);
    List<Payment> findByUserIdAndStatusAndDueToBetween(Long userId, PaymentStatus status, LocalDate startDate, LocalDate endDate);
    List<Payment> findByUserIdAndCategoryIdAndDueToBetween(Long userId, Long categoryId, LocalDate startDate, LocalDate endDate);
    List<Payment> findByUserIdAndCategoryIdAndStatusAndDueToBetween(Long userId, Long categoryId, PaymentStatus status, LocalDate since, LocalDate dueTo);
}
