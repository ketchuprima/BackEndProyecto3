package com.back.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	public Boolean existsByNom(String nom);
	
	public Empresa findByNom(String nom);
}
