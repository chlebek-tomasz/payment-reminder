package nwta.paymentreminder.model.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {
    @NotNull(message = "Title field cannot be null!")
    @Size(min=3, max=40)
    private String title;
    @NumberFormat
    @NotNull(message = "Recipient account number field cannot be null!")
    private String recipientAccountNumber;
    @NotNull(message = "Recipient field cannot be null!")
    private String recipient;
    @NotNull(message = "Amount field cannot be null!")
    @NumberFormat
    private double amount;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dueTo;
    private int periodicity;
    @NotNull(message = "CategoryId field cannot be null!")
    private Long categoryId;
}
