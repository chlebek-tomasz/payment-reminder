package nwta.paymentreminder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentStatisticDTO {
    private String since;
    private String dueTo;
    private double summaryAmount;
    private int paymentCounts;
    private List<PaymentDTO> paymentDTOs;
}
