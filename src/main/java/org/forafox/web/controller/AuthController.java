package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.forafox.service.AuthService;
import org.forafox.service.UserService;
import org.forafox.web.dto.UserDto;
import org.forafox.web.dto.auth.JwtRequest;
import org.forafox.web.dto.auth.JwtResponse;
import org.forafox.web.dto.validation.OnCreate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth controller", description = "Auth API")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody SignInRequest loginRequest) {
        return authService.login(new JwtRequest(loginRequest.email, loginRequest.password));
    }

    @PostMapping("/register")
    public JwtResponse register(@Validated(OnCreate.class)
                                @RequestBody final SignUpRequest request) {
        var user = userService.create(new UserDto(null, request.name, request.email, request.password));
        return authService.login(new JwtRequest(user.getEmail(), request.password));
    }


    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }

    record SignUpRequest(@Email @NotNull String email, @NotNull String password, @NotNull String name) {
    }

    record SignInRequest(@Email @NotNull String email, @NotNull String password) {
    }
}
