package org.dreamsellers.controller;

import lombok.AllArgsConstructor;
import org.api.AuthApi;
import org.dreamsellers.service.AuthService;
import org.model.AuthenticatedUserDto;
import org.model.LoginDto;
import org.model.RegisterDto;
import org.model.AuthenticatedUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    @Autowired
    private AuthService authService;


    @Override
    public ResponseEntity<AuthenticatedUserDto> userRegister(RegisterDto registerDto) {
        AuthenticatedUserDto registerResponseDto = authService.register(registerDto);

        return new ResponseEntity<>(registerResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthenticatedUserDto> userLogin(LoginDto loginDto) {
        AuthenticatedUserDto authenticatedUserDto = authService.login(loginDto);

        return new ResponseEntity<>(authenticatedUserDto, HttpStatus.OK);
    }
}
