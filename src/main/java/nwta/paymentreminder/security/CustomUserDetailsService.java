package nwta.paymentreminder.security;

import nwta.paymentreminder.exception.ResourceNotFoundException;
import nwta.paymentreminder.model.User;
import nwta.paymentreminder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(usernameOrEmail)
                .orElseThrow(() -> new ResourceNotFoundException());
        user.setLastLoggedIn(new Date());
        userRepository.save(user);
        return UserPrincipal.build(user);
    }
}
