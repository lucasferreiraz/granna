package io.lucasprojects.granna.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.lucasprojects.granna.domain.model.User;
import io.lucasprojects.granna.domain.repository.UserRepository;

@Component
public class UserDetailsSecurityServer implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByEmail(username);
        if(optUser.isEmpty()) {
            throw new UsernameNotFoundException("Invalid Username or password");
        }

        return optUser.get();
    }
    
}
