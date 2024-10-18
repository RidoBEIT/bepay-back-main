package com.beIt.entities;


import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data 
@Table(name = "membre")
public class Membre extends Auditable<String>{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMembre;
	@Column(unique=true)
    private int code;    
    @Column
    private String email;  
    @Column(unique=true)
    private String telephone;
    private String nom;
    private String prenom;    
    private String adresse;
    private String numeroIdentite;    
    private Boolean statut=false;
    
//    @ManyToMany
//	@JoinTable(
//	  name = "membres_tontines", 
//	  joinColumns = @JoinColumn(name = "id_membre"), 
//	  inverseJoinColumns = @JoinColumn(name = "id_tontine"))
//	private Collection<Tontine> tontines;
    
	public Membre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

