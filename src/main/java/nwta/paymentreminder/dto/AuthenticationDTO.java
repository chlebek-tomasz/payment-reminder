package nwta.paymentreminder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nwta.paymentreminder.model.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthenticationDTO {
    private UserDTO user;
    private String token;

}
