package com.tobeto.stockcontrol2.dto.login;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
	@Size(min = 3, message = "Email must be between 3 and 50 characters")
	private String email;
	private String password;
}
