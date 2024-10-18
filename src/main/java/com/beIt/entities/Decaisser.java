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
@Table(name = "decaisser")
@Data
public class Decaisser extends Auditable<String>{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
	protected long idDecaisser;
	
	@Column(name="code")
	protected String code;
		
	@Column(name="nom_client")
	protected String nom;
	
	@Column(name="libelle_decaisser")
	protected String libelle_decaisser;
	
	@Column(name="prenom_client")
	protected String prenom;
	
	@Column(name="montant")
	protected int montant;
	
	private String statut = "INITIEE";
	
	@Column(name="numero", nullable=false)
	protected String numero;
	
	@Column(name="response_enquiry", columnDefinition = "TEXT", nullable=true)
	protected String responseEnquiry;
	
	@ManyToOne
	protected TypeTransaction typeTransaction;
	
	@ManyToOne
	protected Partenaire partenaire;
	
	@ManyToOne
	protected Application appClient;
	
	@ManyToOne
	protected Evenement evenement;
	
	@Column(name="comtpeDestinateur")
	protected String comtpeDestinateur;
}
