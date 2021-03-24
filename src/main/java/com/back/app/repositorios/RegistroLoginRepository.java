package com.back.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.RegistroLogin;

@Repository
public interface RegistroLoginRepository extends JpaRepository<RegistroLogin, Long> {

}
