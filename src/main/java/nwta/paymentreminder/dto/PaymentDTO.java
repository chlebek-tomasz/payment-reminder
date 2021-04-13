package nwta.paymentreminder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nwta.paymentreminder.enums.PaymentStatus;
import nwta.paymentreminder.model.PaymentCategory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDTO {
    private Long id;
    private PaymentStatus status;
    private String title;
    private String recipientAccountNumber;
    private String recipient;
    private double amount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueTo;
    private PaymentCategory category;
}
