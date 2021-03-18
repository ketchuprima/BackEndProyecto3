package com.back.app.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByNombre(String nombre);

}
