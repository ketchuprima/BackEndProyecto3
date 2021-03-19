package com.back.app.requests;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {
    
	@Size(max = 50)
	@NotNull
	@Email
	private String email;
    
    @NotNull
    @Size(max = 120)
    private String pass;
    
    @Size(max = 50)
	@NotNull
	private String nom;
	
	@Size(max = 100)
	@NotNull
	private String cognoms;
	
	@Size(min = 12, max = 12)
	@NotNull
	private String telefon;
    
    @Size(max = 45)
    private Set<String> role;
    
    @Size(max = 100)
    private String nomEmpresa;
    
    @Size(max = 255)
    private String tipus;
    
    @Size(max = 255)
    private String logo;
    
    @Size(max = 255)
    @Email
    private String correu;
}
