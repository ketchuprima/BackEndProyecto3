package com.back.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.RegistroCV;

@Repository
public interface RegistroCVRepository extends JpaRepository<RegistroCV, Long>{

}
