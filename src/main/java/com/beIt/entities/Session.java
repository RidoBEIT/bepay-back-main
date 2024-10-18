package com.beIt.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "session")
@Data
public class Session extends Auditable<String> {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSession;
	
	@Column
	private String token;
	
	@Column
	private String token_type;
	
	@Column
	private String expireDate;
	
	@Column
	private Boolean isCurrent = true;
	
	@ManyToOne()
	private Partenaire partenaire;
	
	
}
