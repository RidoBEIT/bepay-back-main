package com.beIt.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "session_tontine_transaction")
@Data
public class SessionTontineTransaction extends Auditable<String> {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
	protected long idSessionTontineTransaction;
	
	@Column(name="code")
	protected String code;
		
	@ManyToOne
	private Membre membre;
	
	@Column(name="montant")
	protected int montant;
	
	private String statut = "INITIEE";
	
	@ManyToOne
	protected Partenaire partenaire;
	
	@ManyToOne
	protected SessionTontine sessionTontine;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "idTransaction")
	private Transaction transaction;
}
