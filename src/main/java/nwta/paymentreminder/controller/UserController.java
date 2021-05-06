package nwta.paymentreminder.controller;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.dto.AuthenticationDTO;
import nwta.paymentreminder.dto.UserDTO;
import nwta.paymentreminder.model.payload.ChangeEmailRequest;
import nwta.paymentreminder.model.payload.ChangePasswordRequest;
import nwta.paymentreminder.model.payload.ChangeUserDetailsRequest;
import nwta.paymentreminder.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/{id}/change-password")
    public ResponseEntity<AuthenticationDTO> changePassword(@PathVariable("id") Long id,
                                                            @RequestBody ChangePasswordRequest request) {
        return new ResponseEntity<>(service.changePassword(id, request), HttpStatus.OK);
    }

    @PostMapping("/{id}/change-email")
    public ResponseEntity<UserDTO> changeEmail(@PathVariable("id") Long id,
                                               @RequestBody ChangeEmailRequest request) {
        return new ResponseEntity<>(service.changeEmail(id, request), HttpStatus.OK);
    }

    @PostMapping("/{id}/change-details")
    public ResponseEntity<UserDTO> changeUserDetails(@PathVariable("id") Long id,
                                                     @RequestBody ChangeUserDetailsRequest request) {
        return new ResponseEntity<>(service.changeUserDetails(id, request), HttpStatus.OK);
    }
}
