package com.beIt.entities;

import java.sql.Date;
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
@Table(name = "session_tontine")
@Data
public class SessionTontine extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSessionTontine;
	
	@Column(name="libelle")
	private String libelle;
	
	@Column(columnDefinition="varchar(20000)")
	private String description;
	
	private double montant;
	
	private double totalCollecte;
	
	private Date dateDebut;
	
	private Date dateFin;
	
	@ManyToOne
	private  Tontine tontine;
	
}
