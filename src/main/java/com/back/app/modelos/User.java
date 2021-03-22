package com.back.app.modelos;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 255)
	@Email
	@NotNull
	private String email;
	
	@Size(max = 255)
	@NotNull
	@JsonBackReference(value = "userPass")
	private String pass;
	
	@Size(max = 50)
	@NotNull
	private String nom;
	
	@Size(max = 100)
	@NotNull
	private String cognoms;
	
	@Size(min = 9, max = 9)
	@NotNull
	private String telefon;
	
	
	@ManyToMany
	@JoinTable(name = "rol_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles;
	
	public User(String email, String pass) {
		this.email=email;
		this.pass=pass;
		
	}
	
	private static final long serialVersionUID = -2422977159875418682L;

}
