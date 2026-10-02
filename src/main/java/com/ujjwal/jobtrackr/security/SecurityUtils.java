package com.ujjwal.jobtrackr.security;

import com.ujjwal.jobtrackr.entity.User;
import com.ujjwal.jobtrackr.exception.ResourceNotFoundException;
import com.ujjwal.jobtrackr.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class SecurityUtils {

    private final UserRepository userRepository;

    public SecurityUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder
                .getContext().getAuthentication();

        String email = auth.getName(); // email is the principal

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", 0L));
    }
}
