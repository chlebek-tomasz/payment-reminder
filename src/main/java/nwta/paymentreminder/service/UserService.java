package nwta.paymentreminder.service;

import lombok.AllArgsConstructor;
import nwta.paymentreminder.dto.UserDTO;
import nwta.paymentreminder.exception.ResourceExistsException;
import nwta.paymentreminder.exception.ResourceForbiddenException;
import nwta.paymentreminder.exception.ResourceNotFoundException;
import nwta.paymentreminder.model.User;
import nwta.paymentreminder.model.payload.ChangeEmailRequest;
import nwta.paymentreminder.model.payload.ChangePasswordRequest;
import nwta.paymentreminder.model.payload.ChangeUserDetailsRequest;
import nwta.paymentreminder.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final AuthService service;

    public void changePassword(Long userId, ChangePasswordRequest request) {
        User user = repository.findById(userId).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        if (user.getId() != service.getCurrentUser().getId()) {
            throw new ResourceForbiddenException();
        }
        if (encoder.matches(request.getOldPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(request.getNewPassword()));
            repository.save(user);
        } else {
            throw new ResourceForbiddenException();
        }
    }

    public UserDTO changeEmail(Long userId, ChangeEmailRequest request) {
        User user = repository.findById(userId).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        if (user.getId() != service.getCurrentUser().getId()) {
            throw new ResourceForbiddenException();
        }
        if (repository.existsByEmail(request.getNewEmail())) {
            throw new ResourceExistsException();
        }
        if (user.getEmail().equals(request.getOldEmail())) {
            user.setEmail(request.getNewEmail());
            repository.save(user);
            return UserDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .build();
        } else {
            throw new ResourceForbiddenException();
        }
    }

    public UserDTO changeUserDetails(Long userId, ChangeUserDetailsRequest request) {
        User user = repository.findById(userId).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        if (user.getId() != service.getCurrentUser().getId()) {
            throw new ResourceForbiddenException();
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        repository.save(user);
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
