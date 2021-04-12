package nwta.paymentreminder.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
