package com.back.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.Candidat;
import com.back.app.modelos.User;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long>{
	
	public Candidat findByUser(User user);
	
	@Query(value="SELECT EXISTS(SELECT * FROM candidat WHERE user_id = :idUser)", nativeQuery=true)
	public Integer existsByUser(Long idUser);

}
