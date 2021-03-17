package com.back.app.controladores;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.app.exceptions.NoEncontradoException;
import com.back.app.modelos.Oferta;
import com.back.app.repositorios.OfertaRepository;
import com.back.app.responses.MensajeRespuesta;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ofertes")
public class OfertaController {
	
	@Autowired
	private OfertaRepository ofertaRepository;
	
	@GetMapping("/")
	public List<Oferta> findOferta(){
		return ofertaRepository.findAll();
	}
	
	@PostMapping("/crear")
	public Oferta createOferta(@Valid @RequestBody Oferta oferta) {
		oferta.setId(null);
		oferta.setData_de_publicacio(LocalDateTime.now());
		oferta.setCandidats(null);
		return ofertaRepository.save(oferta);
	}
	
	@PutMapping("/actualizar/{idOferta}")
	public Oferta updateOferta(@Valid @RequestBody Oferta oferta, @PathVariable Long idOferta) throws NoEncontradoException {
		Oferta ofertaSinActualizar = null;
		if(ofertaRepository.existsById(idOferta)) {
			ofertaSinActualizar = ofertaRepository.findById(idOferta).get();
			oferta.setId(ofertaSinActualizar.getId());
			oferta.setEmpresa(ofertaSinActualizar.getEmpresa());
			oferta.setCandidats(ofertaSinActualizar.getCandidats());
			ofertaRepository.save(oferta);
		}else
			throw new NoEncontradoException("No existe esta empresa");
		
		return ofertaRepository.findById(idOferta).get();
	}
	
	@DeleteMapping("eliminar/{idOferta}")
	public MensajeRespuesta deleteOferta(@PathVariable Long idOferta) throws NoEncontradoException {
		
		if(ofertaRepository.existsById(idOferta)) 
			ofertaRepository.deleteById(idOferta);
		else
			throw new NoEncontradoException("No existe esta empresa");
		return new MensajeRespuesta("ok");
	}

}
