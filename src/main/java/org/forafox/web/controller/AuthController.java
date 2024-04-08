package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.forafox.service.AuthService;
import org.forafox.service.UserService;
import org.forafox.web.dto.UserDto;
import org.forafox.web.dto.auth.JwtRequest;
import org.forafox.web.dto.auth.JwtResponse;
import org.forafox.web.requestRecord.SignInRequest;
import org.forafox.web.requestRecord.SignUpAdminRequest;
import org.forafox.web.requestRecord.SignUpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth controller", description = "Auth API")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody SignInRequest loginRequest) {
        return authService.login(new JwtRequest(loginRequest.email(), loginRequest.password()));
    }

    @PostMapping("/register")
    public JwtResponse register(@Valid @RequestBody final SignUpRequest request) {
        var user = userService.create(new UserDto(null, request.name(), request.email(), request.password()));
        return authService.login(new JwtRequest(user.getEmail(), request.password()));
    }

    @PostMapping("/register/admin")
    public JwtResponse registerAdmin(@Valid @RequestBody final SignUpAdminRequest request) {
        var user = userService.adminCreate(new UserDto(null, request.name(), request.email(), request.password()), request.adminKey());
        return authService.login(new JwtRequest(user.getEmail(), request.password()));
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@Valid @RequestBody @NotBlank String refreshToken) {
        return authService.refresh(refreshToken);
    }

}
