package com.back.app.controladores;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.back.app.exceptions.NoEncontradoException;
import com.back.app.modelos.Oferta;
import com.back.app.repositorios.OfertaRepository;
import com.back.app.responses.EstadisticasCategoria;
import com.back.app.responses.MensajeRespuesta;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ofertes")
public class OfertaController {
	
	//Estado 0 = para validar por admin
	//Estado 1 = validado por un admin
	
	@Autowired
	private OfertaRepository ofertaRepository;
	
	@GetMapping("/")
	public List<Oferta> findOferta(){
		return ofertaRepository.findByEstat(1);
	}
	
	@GetMapping("/perValidar")
	public List<Oferta> findOfertaPerValidar(){
		return ofertaRepository.findByEstat(0);
	}
	
	@GetMapping("/{idOferta}")
	public Optional<Oferta> findOfertaById(@PathVariable Long idOferta) {
		return ofertaRepository.findById(idOferta);
	}
	
	@GetMapping("/estadisticas/categorias")
	public EstadisticasCategoria findEstadisticasCategoria(){
		
		List<Oferta> listaOfertas = ofertaRepository.findByEstat(1);
		
		EstadisticasCategoria estadisticasCategorias = new EstadisticasCategoria();

		for(Oferta o: listaOfertas) {
			if(o.getCategoria().getNom().equals("DAM")) {
				estadisticasCategorias.setDam(estadisticasCategorias.getDam() + 1);
			}else if(o.getCategoria().getNom().equals("DAW")) {
				estadisticasCategorias.setDaw(estadisticasCategorias.getDaw() + 1);
			}else if(o.getCategoria().getNom().equals("ASIX")) {
				estadisticasCategorias.setAsix(estadisticasCategorias.getAsix() + 1);
			}else {
				estadisticasCategorias.setSmix(estadisticasCategorias.getSmix() + 1);
			}
		}
			
		return estadisticasCategorias;
	}
	
	@GetMapping("/estadisticas/ofertas")
	public EstadisticasCategoria findEstadisticasOfertas() {
		List<Oferta> listaOfertas = ofertaRepository.findByEstat(1);
		
		EstadisticasCategoria estadisticasCategorias = new EstadisticasCategoria();

		for(Oferta o: listaOfertas) {
			if(o.getCategoria().getNom().equals("DAM")) {
				estadisticasCategorias.setDam(estadisticasCategorias.getDam() + o.getCandidats().size());
			}else if(o.getCategoria().getNom().equals("DAW")) {
				estadisticasCategorias.setDaw(estadisticasCategorias.getDaw() + o.getCandidats().size());
			}else if(o.getCategoria().getNom().equals("ASIX")) {
				estadisticasCategorias.setAsix(estadisticasCategorias.getAsix() + o.getCandidats().size());
			}else {
				estadisticasCategorias.setSmix(estadisticasCategorias.getSmix() + o.getCandidats().size());
			}
		}
		
		return estadisticasCategorias;
	}
	
	@GetMapping("/filtros")
	public List<Oferta> prueba(@RequestParam(value = "categoria", required = false) Long categoria,
			@RequestParam(value = "ubicacio", required = false) String ubicacio,
			@RequestParam(value = "titol", required = false) String titol,
			@RequestParam(value = "empresa", required = false) Long empresa,
			@RequestParam(value = "order", required = false) String order){
		
		
		
		return ofertaRepository.findfertasByParameters(categoria, ubicacio, titol, empresa, order);
		
	}
	
	@PostMapping("/crear")
	public Oferta createOferta(@Valid @RequestBody Oferta oferta) {
		oferta.setId(null);
		oferta.setData_de_publicacio(LocalDateTime.now());
		oferta.setCandidats(null);
		oferta.setEstat(0);
		
		return ofertaRepository.save(oferta);
	}
	
	@PutMapping("/actualizar/{idOferta}")
	public Oferta updateOferta(@Valid @RequestBody Oferta oferta, @PathVariable Long idOferta) throws NoEncontradoException {
		Oferta ofertaSinActualizar = null;
		if(ofertaRepository.existsById(idOferta)) {
			ofertaSinActualizar = ofertaRepository.findById(idOferta).get();
			oferta.setId(ofertaSinActualizar.getId());
			oferta.setData_de_publicacio(ofertaSinActualizar.getData_de_publicacio());
			oferta.setEmpresa(ofertaSinActualizar.getEmpresa());
			oferta.setCandidats(ofertaSinActualizar.getCandidats());
			oferta.setEstat(ofertaSinActualizar.getEstat());
			ofertaRepository.save(oferta);
		}else
			throw new NoEncontradoException("No existe esta oferta");
		
		return ofertaRepository.findById(idOferta).get();
	}
	
	@PutMapping("actualizarEstado/{idOferta}")
	public MensajeRespuesta updateEstadoOferta(@PathVariable Long idOferta) throws NoEncontradoException {
		if(ofertaRepository.existsById(idOferta)) {
			Oferta oferta = ofertaRepository.findById(idOferta).get();
			oferta.setEstat(1);
			ofertaRepository.save(oferta);
		}else
			throw new NoEncontradoException("No existe esta oferta");
		
		return new MensajeRespuesta("ok");
	}
	
	@DeleteMapping("eliminar/{idOferta}")
	public MensajeRespuesta deleteOferta(@PathVariable Long idOferta) throws NoEncontradoException {
		
		if(ofertaRepository.existsById(idOferta)) 
			ofertaRepository.deleteById(idOferta);
		else
			throw new NoEncontradoException("No existe esta oferta");
		return new MensajeRespuesta("ok");
	}

}
