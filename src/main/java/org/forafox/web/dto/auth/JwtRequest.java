package org.forafox.web.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtRequest {
    @NotNull(message = "Username must be not null")
    private String username;
    @NotNull(message = "Password must be not null")
    private String password;
}
