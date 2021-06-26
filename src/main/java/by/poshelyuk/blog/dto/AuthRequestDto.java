package by.poshelyuk.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequestDto {
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}
