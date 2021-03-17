package com.back.app.modelos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "empresa")
public class Empresa implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	@NotNull
	private User user;
	
	@Size(max = 100)
	@NotNull
	private String nom;
	
	@Size(max = 255)
	@NotNull
	private String tipus;
	
	@Size(max = 500)
	@NotNull
	private String logo;
	
	@Size(max = 255)
	@Email
	@NotNull
	private String correu;
	
	@OneToMany(mappedBy="empresa")
	@JsonBackReference(value = "empresaOferta")
	private List<Oferta> ofertes;
	
	private static final long serialVersionUID = -1085921810573003719L;

}
