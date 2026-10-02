package com.ujjwal.jobtrackr.service;

import com.ujjwal.jobtrackr.dto.AuthRequest;
import com.ujjwal.jobtrackr.dto.AuthResponse;
import com.ujjwal.jobtrackr.entity.Role;
import com.ujjwal.jobtrackr.entity.User;
import com.ujjwal.jobtrackr.exception.BadRequestException;
import com.ujjwal.jobtrackr.exception.ResourceNotFoundException;
import com.ujjwal.jobtrackr.repository.UserRepository;
import com.ujjwal.jobtrackr.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(AuthRequest authRequest){
        if(userRepository.existsByEmail(authRequest.getEmail())){
            throw new BadRequestException("Email already registered : " + authRequest.getEmail());
        }

        User user = User.builder().email(authRequest.getEmail()).password(passwordEncoder.encode(authRequest.getPassword())).name(authRequest.getName()).role(Role.USER).build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(),
                user.getName(), user.getRole().name());
    }


    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", 0L));

        if (!passwordEncoder.matches(
                request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(),
                user.getName(), user.getRole().name());
    }


}

