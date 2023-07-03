package by.tkach.news.controllers;

import by.tkach.news.dto.auth.request.AuthRequest;
import by.tkach.news.dto.auth.request.UserRegistrationRequest;
import by.tkach.news.dto.auth.response.AuthResponse;
import by.tkach.news.mappers.UserMapper;
import by.tkach.news.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Class auth controller handles all request that are related to authentification operations.
 * @author Grisha Tkach
 * @version 1.0.0
 */
@RestController
@AllArgsConstructor
public class AuthController {
    /**
     * Field contains authentification logic.
     */
    private final AuthService authService;

    /** Field contains methods for type conversion */
    private final UserMapper userMapper;

    /**
     * Returns a {@link AuthResponse} that include.
     * @param authRequest {@link AuthRequest} - must be not null.
     * @return {@link AuthResponse} - this object has a token.
     */
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest authRequest) {
        return authService.loginUser(authRequest);
    }

    /**
     * Create new user {@link by.tkach.news.entities.User} in system.
     * @param userRegistrationRequest  {@link UserRegistrationRequest} object with user data for registration.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        authService.registerUser(userMapper.toUser(userRegistrationRequest));
    }
}
