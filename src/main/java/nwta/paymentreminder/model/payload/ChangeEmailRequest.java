package nwta.paymentreminder.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangeEmailRequest {
    private String oldEmail;
    private String newEmail;
}
