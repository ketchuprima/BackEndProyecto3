package com.back.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.app.modelos.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
