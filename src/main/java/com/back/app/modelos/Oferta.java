package com.back.app.modelos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "oferta")
public class Oferta implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer estat;
	
	@Size(max = 50)
	private String titol;
	
	@Size(max = 1000)
	private String descripcio;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime data_de_publicacio;
	
	@Size(max = 50)
	private String ubicacio;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	private Empresa empresa;
	
	@ManyToMany
	@JoinTable(name = "candidat_oferta", joinColumns = @JoinColumn(name = "oferta_id"), inverseJoinColumns = @JoinColumn(name = "candidat_id"))
	private List<Candidat> candidats; 

	private static final long serialVersionUID = -8119604915282073567L;

}
