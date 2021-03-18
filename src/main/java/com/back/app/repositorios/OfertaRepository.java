package com.back.app.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long>{
	
	public List<Oferta> findByEstat(Integer estat);

}
