package com.back.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.Candidat;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long>{

}
