package com.back.app.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.app.modelos.Candidat;
import com.back.app.modelos.Oferta;
import com.back.app.modelos.User;
import com.back.app.repositorios.CandidatRepository;
import com.back.app.repositorios.OfertaRepository;
import com.back.app.repositorios.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/candidats")
public class CandidatController {
	
	@Autowired
	private CandidatRepository candidatRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OfertaRepository ofertaRepository;
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/crearCandidatura/{idOferta}")
	public Candidat createCandidat(Authentication authentication, @PathVariable Long idOferta) {
		String email = authentication.getName();
		User user = userRepository.findByEmail(email).get();
		List<Oferta> ofertesCandidat = new ArrayList<>();
		if(candidatRepository.existsByUser(user.getId()) == 0) {
			Candidat candidat = new Candidat();
			candidat.setId(null);
			candidat.setUser(user);
			ofertesCandidat.add(ofertaRepository.findById(idOferta).get());
			candidat.setOfertes(ofertesCandidat);
			
		}else {
			Candidat candidat = candidatRepository.findByUser(user);
			for(Oferta o: candidat.getOfertes()) {
				ofertesCandidat.add(o);
			}
			ofertesCandidat.add(ofertaRepository.findById(idOferta).get());
			candidat.setOfertes(ofertesCandidat);
		}
		/*User user = userRepository.findByEmail(email).get();
		
		candidatRepository.findByUser(user);
		Candidat candidat = new Candidat();
		candidatRepository.findBy
		candidat.setId(null);
		candidat.setUser();
		candidat.setOfertes(ofertes);*/
		
		return null;
	}

}
