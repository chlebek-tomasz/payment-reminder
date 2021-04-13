package nwta.paymentreminder.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {

    @NotNull
    @Email(message = "Email field must be email and contain '@'")
    private String email;
    @NotNull
    @Size(min = 6, max = 80, message = "Password size must be beetwen 6 and 80")
    private String password;
    @NotNull
    @Size(min = 3)
    private String firstName;
    @NotNull
    @Size(min = 3)
    private String lastName;
}
