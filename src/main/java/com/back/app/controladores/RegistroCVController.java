package com.back.app.controladores;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.app.modelos.RegistroCV;
import com.back.app.repositorios.OfertaRepository;
import com.back.app.repositorios.RegistroCVRepository;
import com.back.app.repositorios.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/registroCV")
public class RegistroCVController {
	
	@Autowired
	private RegistroCVRepository registroCVRepository;
	
	@Autowired
	private OfertaRepository ofertaRepository;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/{idOferta}")
	public RegistroCV createRegistroCV(Authentication authentication, @PathVariable Long idOferta, @RequestBody @Valid RegistroCV registroCV) {
		String email = authentication.getName();
		
		registroCV.setId(null);
		registroCV.setData_de_enviament(LocalDateTime.now());
		registroCV.setOferta(ofertaRepository.findById(idOferta).get());
		registroCV.setUser(userRepository.findByEmail(email).get());
		
		return registroCVRepository.save(registroCV);
	}
}
