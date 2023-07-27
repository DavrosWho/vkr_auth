package org.example.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.example.auth.exceptions.LoginException;
import org.example.auth.exceptions.RegistrationException;
import org.example.auth.mappers.UserToDtoMapper;
import org.example.auth.models.ErrorResponse;
import org.example.auth.models.TokenResponse;
import org.example.auth.models.User;
import org.example.auth.models.UserRequest;
import org.example.auth.services.RegistrationService;
import org.example.auth.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final TokenService tokenService;

    private final UserToDtoMapper mapper;

    @PostMapping
    public TokenResponse register(@RequestBody UserRequest request) {
        registrationService.register(mapper.AddUserRequestToUser(request));
        return new TokenResponse(tokenService.generateToken(request.getUsername()));
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody User user) {
        registrationService.checkCredentials(user.getUsername(), user.getPassword());
        return new TokenResponse(tokenService.generateToken(user.getUsername()));
    }

    @ExceptionHandler({RegistrationException.class, LoginException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
}