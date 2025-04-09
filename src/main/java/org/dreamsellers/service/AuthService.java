package org.dreamsellers.service;

import lombok.AllArgsConstructor;
//import org.dreamsellers.errors.UserAlreadyExistsException;
import org.dreamsellers.model.AuthEntity;
import org.dreamsellers.model.UserEntity;
import org.dreamsellers.repository.AuthRepository;
import org.dreamsellers.repository.UserRepository;
import org.model.LoginDto;
import org.model.RegisterDto;
import org.model.AuthenticatedUserDto;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthService {
    private final AuthRepository authRepository;
//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
//    private final JWTService jwtService;

    public AuthenticatedUserDto register(RegisterDto registerDto) {
//        Optional<AuthEntity> existingAuth = authRepository.findByUsername(registerDto.getUsername());
//
//        if (existingAuth.isPresent()) throw UserAlreadyExistsException.create(registerDto.getUsername());

        UserEntity user = new UserEntity();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhone());
        UserEntity savedUser = userRepository.save(user);



        AuthEntity auth = new AuthEntity();
//        auth.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        auth.setPassword(registerDto.getPassword());
        auth.setUsername(registerDto.getUsername());
        auth.setRole(registerDto.getRole());
        auth.setUser(savedUser);

        AuthEntity savedAuth = authRepository.save(auth);
        AuthenticatedUserDto authenticatedUserDto = new AuthenticatedUserDto();
        authenticatedUserDto.setUid(savedAuth.getId());
        return authenticatedUserDto;

    }

    public AuthenticatedUserDto login(LoginDto loginDto) {
        Optional<AuthEntity> auth = authRepository.findByUsername(loginDto.getUsername());
        if (auth.isEmpty()) throw new RuntimeException("Invalid username");
        if (!auth.get().getPassword().equals(loginDto.getPassword())) throw new RuntimeException("Invalid password");

        AuthenticatedUserDto authenticatedUserDto = new AuthenticatedUserDto();
        authenticatedUserDto.setUid(auth.get().getId());

        return authenticatedUserDto;
    }
}
