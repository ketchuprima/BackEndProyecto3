package com.back.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.Empresa;
import com.back.app.modelos.User;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	public Boolean existsByNom(String nom);
	
	public Empresa findByNom(String nom);
	
	public Empresa findByUser(User user);
	
	@Query(value="SELECT EXISTS(SELECT * FROM empresa WHERE user_id = :idUsuario)", nativeQuery=true)
	public Integer existsByUser(Long idUsuario);
}
