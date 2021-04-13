package nwta.paymentreminder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nwta.paymentreminder.enums.PaymentStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private PaymentStatus status;
    private String title;
    private String recipientAccountNumber;
    private String recipient;
    private double amount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueTo;
    private int periodicity;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "category_id", referencedColumnName = "id")
    })
    private PaymentCategory category;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    })
    private User user;
}
