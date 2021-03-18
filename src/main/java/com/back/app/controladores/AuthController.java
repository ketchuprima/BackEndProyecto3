package com.back.app.controladores;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.app.exceptions.NoEncontradoException;
import com.back.app.modelos.Rol;
import com.back.app.modelos.User;
import com.back.app.repositorios.RolRepository;
import com.back.app.repositorios.UserRepository;
import com.back.app.requests.LoginRequest;
import com.back.app.requests.SignupRequest;
import com.back.app.responses.JwtResponse;
import com.back.app.responses.MensajeRespuesta;
import com.back.app.security.jwt.JwtUtils;
import com.back.app.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RolRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	MessageSource messageSource;

	private ResponseEntity<?> resultado;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result)
			throws NoEncontradoException {

		if (result.hasErrors()) {
			throw new NoEncontradoException("Login request no valido");

		} else {

			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequest.getEmail(), loginRequest.getPass()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));

		}

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest)
			throws NoEncontradoException {

		if (userRepository.existsByEmail(signUpRequest.getEmail()) == 1) {
			throw new NoEncontradoException("Ya existe un alguien con este nombre de usuario");
		}

		User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPass()));
		Set<String> strRoles = signUpRequest.getRole();
		// System.out.println(strRoles);
		// System.out.println(user.getIdPersona().getIdPersona());
		Set<Rol> roles = new HashSet<>();

		if (strRoles == null) {
			Rol userRole = roleRepository.findByNombre("ROLE_USER")
					.orElseThrow(() -> new RuntimeException("No se encuentra el rol user"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					Rol adminRole = roleRepository.findByNombre("ROLE_ADMIN")
							.orElseThrow(() -> new RuntimeException("No se encuentra el rol admin"));
					roles.add(adminRole);

					break;
				default:
					Rol userRole = roleRepository.findByNombre("ROLE_USER")
							.orElseThrow(() -> new RuntimeException("No se encuentra el rol user"));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		user.setNom(signUpRequest.getNom());
		user.setCognoms(signUpRequest.getCognoms());
		user.setTelefon(signUpRequest.getTelefon());
		userRepository.save(user);
		resultado = ResponseEntity.ok(new MensajeRespuesta("El usuario ha sido registrado correctamente"));

		return resultado;
	}

}
