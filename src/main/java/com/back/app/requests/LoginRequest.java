package com.back.app.requests;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
	
	@NotNull
	private String email;

	@NotNull
	private String pass;
}
