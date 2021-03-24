package com.back.app.daoImplements;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.Oferta;
import com.back.app.repositorios.OfertaRepositoryCustom;

@Repository
public class OfertaRepositoryImpl implements OfertaRepositoryCustom{

	@Autowired
	EntityManager em;	
	
	@Override
	public List<Oferta> findfertasByParameters(Long categoria, String ubicacio, String titol, Long empresa,
			String order) {
		
		String select = "SELECT * FROM oferta WHERE estat = 1 AND data_de_publicacio > DATE_SUB(now(), INTERVAL 3 MONTH)";

        if(categoria != null)
        	select += " AND categoria_id = " + categoria;
  
        if(ubicacio != null)
        	select += " AND ubicacio = '" + ubicacio + "'";

        if(titol != null)
        	select += " AND titol = '" + titol + "'";

        if(empresa != null)
        	select += " AND empresa_id = " + empresa;
        
        if(order != null)
        	select += " ORDER BY data_de_publicacio " + order;
        
		
        @SuppressWarnings("unchecked")
		List <Oferta> query = em.createNativeQuery(select, Oferta.class).getResultList();//.createQuery(select).getResultList();
        return query;
	}

}
