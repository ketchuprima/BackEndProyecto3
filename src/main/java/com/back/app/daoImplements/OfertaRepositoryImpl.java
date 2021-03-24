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
		
		Boolean firstCond = false;
		
		String select = "SELECT * FROM oferta ";

        if(categoria != null && firstCond)
        	select += " AND categoria_id = " + categoria;
        else if(categoria != null && !firstCond){
        	select += "WHERE categoria_id = " + categoria;
        	firstCond = true;
        }
        if(ubicacio != null && firstCond)
        	select += " AND ubicacio = '" + ubicacio + "'";
        else if(ubicacio != null && !firstCond) {
        	select +="WHERE ubicacio = '" + ubicacio + "'";
        	firstCond = true;
        }
        if(titol != null && firstCond)
        	select += " AND titol = '" + titol + "'";
        else if(titol != null && !firstCond) {
        	select += "WHERE titol = '" + titol + "'";
        	firstCond = true;
        }
        if(empresa != null && firstCond)
        	select += " AND empresa_id = " + empresa;
        else if(empresa != null && !firstCond) {
        	select += "WHERE empresa_id = " + empresa;
        	firstCond = true;
        }
        if(order != null)
        	select += " ORDER BY data_de_publicacio " + order;
        
		
        @SuppressWarnings("unchecked")
		List <Oferta> query = em.createNativeQuery(select, Oferta.class).getResultList();//.createQuery(select).getResultList();
        return query;
	}

}
