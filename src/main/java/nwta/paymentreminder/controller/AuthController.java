package nwta.paymentreminder.controller;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.dto.AuthenticationDTO;
import nwta.paymentreminder.model.User;
import nwta.paymentreminder.model.payload.SigninRequest;
import nwta.paymentreminder.model.payload.SignupRequest;
import nwta.paymentreminder.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signup(@RequestBody SignupRequest signupRequest) {
        authService.signup(signupRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationDTO> signin(@RequestBody SigninRequest signinRequest) {
        return new ResponseEntity<>(authService.login(signinRequest), HttpStatus.OK);
    }
}
