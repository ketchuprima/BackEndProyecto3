package com.back.app.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.back.app.modelos.Candidat;
import com.back.app.modelos.Oferta;
import com.back.app.modelos.User;
import com.back.app.repositorios.CandidatRepository;
import com.back.app.repositorios.OfertaRepository;
import com.back.app.repositorios.UserRepository;
import com.back.app.responses.MensajeRespuesta;
import com.back.app.services.EmailService;

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
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/{idUser}")
	public Candidat findCandidat(@PathVariable Long idUser) {
		return candidatRepository.findByUser(userRepository.findById(idUser).get());
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/crearCandidatura/{idOferta}")
	@Transactional
	public MensajeRespuesta createCandidat(Authentication authentication, @PathVariable Long idOferta){
		String email = authentication.getName();
		User user = userRepository.findByEmail(email).get();
		List<Oferta> ofertesCandidat = new ArrayList<>();
		if(candidatRepository.existsByUser(user.getId()) == 0) {
			Candidat candidat = new Candidat();
			candidat.setId(null);
			candidat.setUser(user);
			ofertesCandidat.add(ofertaRepository.findById(idOferta).get());
			candidat.setOfertes(ofertesCandidat);
			candidatRepository.save(candidat);
			
		}else {
			Candidat candidat = candidatRepository.findByUser(user);

			candidat.getOfertes().add(ofertaRepository.findById(idOferta).get());

			candidatRepository.save(candidat);
		}		
		
		return new MensajeRespuesta("ok");
	}
	
	@PostMapping("/enviarCV/{idOferta}")
	public MensajeRespuesta enviarCurriculum(@RequestParam("curriculum") MultipartFile file, Authentication authentication, @PathVariable Long idOferta) throws MessagingException {
		String email = authentication.getName();
		
		emailService.sendSimpleMessage(ofertaRepository.findById(idOferta).get().getEmpresa().getCorreu(), "Solicitud de candidatura de " + email, 
				email, file);
		return new MensajeRespuesta("ok");
	}

}
