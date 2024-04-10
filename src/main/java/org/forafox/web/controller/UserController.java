package org.forafox.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.forafox.service.UserService;
import org.forafox.web.dto.UserDto;
import org.forafox.web.mapper.UserMapper;
import org.forafox.web.responseRecord.GetUserResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@SecurityRequirement(name = "JWT")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID",
            description = "Retrieves user information by their ID",
            operationId = "getUserById", tags = "Admin API")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GetUserResponse getUserById(@PathVariable @Min(0) Long id) {
        return dtoToResponse(userMapper.toDto(userService.getById(id)));
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user",
            description = "Retrieves information about the current user based on the email stored in the JWT payload",
            operationId = "getCurrentUser", tags = "Users")
    public GetUserResponse getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return dtoToResponse(userMapper.toDto(userService.getByEmail(userDetails.getUsername())));
    }

    private GetUserResponse dtoToResponse(UserDto dto) {
        return new GetUserResponse(dto.getId(), dto.getEmail(), dto.getName());
    }


}
