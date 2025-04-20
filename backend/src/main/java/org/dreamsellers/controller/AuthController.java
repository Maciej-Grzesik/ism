package org.dreamsellers.controller;

import lombok.AllArgsConstructor;
import org.api.AuthApi;
import org.dreamsellers.service.AuthService;
import org.model.*;
import org.model.AuthenticatedUserDto;
import org.model.InvalidCredentialsError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController implements AuthApi {
    private AuthService authService;

    @Override
    public ResponseEntity<AuthenticatedUserDto> userRegister(RegisterDto registerDto) {
        AuthenticatedUserDto registerResponseDto = authService.register(registerDto);

        return new ResponseEntity<>(registerResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthenticatedUserDto> userLogin(LoginDto loginDto) {
        try {
            AuthenticatedUserDto authenticatedUserDto = authService.login(loginDto);
            return new ResponseEntity<>(authenticatedUserDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Void> authResetPasswordPost(AuthResetPasswordPostRequest authResetPasswordPostRequest) {
        // reset password logic here

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


        @Override
    public ResponseEntity<Void> logoutUser() {
        // invalidate token logic in auth service

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> authRequestPasswordResetPost(AuthRequestPasswordResetPostRequest authRequestPasswordResetPostRequest) {
        // send email logic here

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
