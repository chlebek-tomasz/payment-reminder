package nwta.paymentreminder.service;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.dto.AuthenticationDTO;
import nwta.paymentreminder.dto.UserDTO;
import nwta.paymentreminder.exception.ResourceExistsException;
import nwta.paymentreminder.exception.ResourceNotFoundException;
import nwta.paymentreminder.model.User;
import nwta.paymentreminder.model.payload.SigninRequest;
import nwta.paymentreminder.model.payload.SignupRequest;
import nwta.paymentreminder.repository.UserRepository;
import nwta.paymentreminder.security.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

    public void signup(SignupRequest request) throws ParseException {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new ResourceExistsException(request.getEmail());
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .registerDate(dt.format(new Date()))
                .build();
        userRepository.save(user);
    }

    public AuthenticationDTO login(SigninRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        return AuthenticationDTO.builder()
                .user(UserDTO.builder().id(user.getId()).email(user.getEmail()).firstName(user.getFirstName()).lastName(user.getLastName()).build())
                .token(Base64.getEncoder().encodeToString((request.getEmail() + ':' + request.getPassword()).getBytes(StandardCharsets.UTF_8)))
                .build();
    }

    @Transactional
    public User getCurrentUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

}
