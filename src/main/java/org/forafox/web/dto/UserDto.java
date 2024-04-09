package org.forafox.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.forafox.web.dto.validation.OnCreate;
import org.forafox.web.dto.validation.OnUpdate;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "Id must be not null.")
    private Long id;

    @NotBlank(message = "Name must be not null or empty.")
    @Length(max = 255, message = "Name length must be smaller than 255 symbols.")
    private String name;

    @NotBlank(message = "Username must be not null or empty.", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Username length must be smaller than 255 symbols.")
    @Email
    private String email;

    @NotBlank(message = "Password must be not null.")
    @Length(max = 255, message = "Password length must be smaller than 255 symbols.")
    private String password;
}
