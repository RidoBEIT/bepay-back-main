/**
 * 
 */
package com.beIt.entities;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;


@Entity
@Table(name = "partenaire")
@Data
//@JsonIdentityInfo(
//		  generator = ObjectIdGenerators.PropertyGenerator.class, 
//		  property = "idCompagnie")
public class Partenaire extends Auditable<String>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPartenaire;
	
	@Column(name="nom_compagnie", nullable=false, unique=true)
	private String nom_partenaire;
	
	@Column(name="telephone", nullable=false, unique=true)
	private String telephone;
	
	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@Column(name="base_url")
	private String baseUrl;
	
	@Column(name="adressePhysique")
	private String adressePhysique;
	
	@Column(name="rccm")
	private String rccm;
	
	@Column(name="nif")
	private String nif;
	
	@Column(name="logo")
	private String logo;
	
	@Column(name="password")
	private String password;
	
	@Column(name="login")
	private String login;
	
//	private String photoUrl;


//	@OneToMany(mappedBy="compagnie", cascade =CascadeType.REMOVE)
//	private Collection<Api> apis;
//	
//	@OneToMany(mappedBy="compagnie",cascade =CascadeType.REMOVE)
//	private Collection<Billet> billets;
	
	
	
	public Partenaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	

	/**
	 * @param raisonSociale
	 * @param telephone
	 * @param email
	 * @param adressePhysique
	 * @param login
	 * @param password
	 * @param authenticateUrl
	 * @param rccm
	 * @param nif
	 * @param apis
	 * @param billets
	 */
	public Partenaire( String telephone, String email, String adressePhysique, String login,
			String password, String authenticateUrl, String rccm, String nif) {
		super();
		
		this.telephone = telephone;
		this.email = email;
		this.adressePhysique = adressePhysique;
		
		this.rccm = rccm;
		this.nif = nif;
		
	}


	
}