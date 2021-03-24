package com.back.app.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long>, OfertaRepositoryCustom{
	
	@Query(value = "SELECT * FROM oferta WHERE estat = :estat AND data_de_publicacio > DATE_SUB(now(), INTERVAL 3 MONTH) ORDER BY data_de_publicacio DESC", nativeQuery = true)
	public List<Oferta> findByEstat(Integer estat);

	public List<Oferta> findfertasByParameters(Long categoriaObj, String ubicacio, String titol, Long empresa,
			String order);

}
