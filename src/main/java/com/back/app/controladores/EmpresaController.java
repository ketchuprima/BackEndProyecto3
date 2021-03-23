package com.back.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.app.exceptions.NoEncontradoException;
import com.back.app.modelos.Empresa;
import com.back.app.modelos.User;
import com.back.app.repositorios.EmpresaRepository;
import com.back.app.repositorios.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/empreses")
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{nom}")
	public Empresa findEmpresa(@PathVariable String nom) throws NoEncontradoException {
		
		if(!empresaRepository.existsByNom(nom))
			throw new NoEncontradoException("No existe esta empresa");
		
		return empresaRepository.findByNom(nom);
	}
	
	@GetMapping("/findByUser/{idUsuario}")
	public Empresa findEmpresaByUser(@PathVariable Long idUsuario) throws NoEncontradoException {
		
		if(empresaRepository.existsByUser(idUsuario) == 0)
			throw new NoEncontradoException("El usuario no tiene ninguna empresa");
		
		return empresaRepository.findByUser(userRepository.findById(idUsuario).get());
	}

}
