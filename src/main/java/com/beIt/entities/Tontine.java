package com.beIt.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tontine")
@Data
public class Tontine extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTontine;
	
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
	
	private double montant;
	
	private String periodicite;
	
	@ManyToOne
	private  User owner;
	
	@ManyToMany
	@JoinTable(
	  name = "membres_tontines", 
	  joinColumns = @JoinColumn(name = "id_tontine"), 
	  inverseJoinColumns = @JoinColumn(name = "id_membre"))
	private Collection<Membre> membres;
	
}
