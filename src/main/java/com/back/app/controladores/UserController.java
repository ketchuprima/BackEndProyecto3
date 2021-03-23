package com.back.app.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.app.exceptions.NoEncontradoException;
import com.back.app.modelos.User;
import com.back.app.repositorios.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/getUser")
	public User getUser(Authentication authentication) throws NoEncontradoException {
		
		if(userRepository.existsByEmail(authentication.getName())==0)
			throw new NoEncontradoException("No existe el usuario");

		return userRepository.findByEmail(authentication.getName()).get();
	}
}
