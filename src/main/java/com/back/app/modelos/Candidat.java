package com.back.app.modelos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "candidat")
public class Candidat implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	@NotNull
	private User user;
	
	@Size(max = 50)
	@NotNull
	private String nom;
	
	@Size(max = 100)
	@NotNull
	private String cognoms;
	
	@Size(min = 11, max = 11)
	@NotNull
	private Integer telefon;
	
	@ManyToMany
	@JoinTable(name = "candidat_oferta", joinColumns = @JoinColumn(name = "oferta_id"), inverseJoinColumns = @JoinColumn(name = "candidat_id"))
	@JsonBackReference(value = "candidatOferta")
	private List<Oferta> ofertes;
	
	private static final long serialVersionUID = 7153466081523768256L;

}
