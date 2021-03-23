package com.back.app.repositorios;

import java.util.List;

import com.back.app.modelos.Oferta;

public interface OfertaRepositoryCustom {
	
	public List<Oferta> findfertasByParameters(Long categoria, String ubicacio, String titol, Long empresa, String order);

}
