package com.back.app.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByEmail(String email);
	
	@Query(value="SELECT EXISTS(SELECT * FROM user WHERE email = :email)", nativeQuery=true)
	public Integer existsByEmail(String email);

}
