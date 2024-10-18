package com.beIt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "evenement")
@Data
public class Evenement extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvenement;
	
	@Column(name="libelle", nullable=false, unique=true)
	private String libelle;
	
	@Column(columnDefinition="varchar(20000)")
	private String description;
	
	@Column 
	private String lien;
	
	@Column 
	private String bouton;
	
	@Column 
	private String photoUrl;
	
	@ManyToOne
	protected User user;
	
}
