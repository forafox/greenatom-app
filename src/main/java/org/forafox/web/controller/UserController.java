package org.forafox.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.forafox.service.UserService;
import org.forafox.web.dto.UserDto;
import org.forafox.web.mapper.UserMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User API")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    @Operation(description = "Get user by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public GetUserResponse getUserById(@PathVariable Long id) {
        return dtoToResponse(userMapper.toDto(userService.getById(id)));
    }

    @GetMapping("/me")
    @Operation(description = "Get user by email in jwt's payload")
    public GetUserResponse getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return dtoToResponse(userMapper.toDto(userService.getByEmail(userDetails.getUsername())));
    }

    private GetUserResponse dtoToResponse(UserDto dto) {
        return new GetUserResponse(dto.getId(), dto.getEmail(), dto.getName());
    }

    record GetUserResponse(@NotNull Long id, @NotNull String email, @NotNull String name) {
    }


}
