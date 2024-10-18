/**
 * 
 */
package com.beIt.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
/**
 * @author Amadou Ali Ousseini
 *
 */
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

/**
 * @author Amadou Ali Ousseini
 *
 */
@Entity
@Table(name="Application")
@Data
public class Application extends Auditable<String>{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
	protected long id;
	
	@Column(name="nom")
	protected String nom;
	
	@Column(columnDefinition="varchar(20000)")
	protected String description;
	
	@Column()
	protected String logo;
	
	@Column()
	protected String cle_public;
	
	
	//@OneToOne(cascade=CascadeType.DETACH)
	//@JoinColumn(name = "user_id")
	//User user;
	
	@ManyToOne
	protected User user;


	@Override
	public String toString() {
		return "Application [id=" + id + ", nom=" + nom + ", description=" + description + ", logo=" + logo
				+ ", cle_public=" + cle_public + ", user=" + user + "]";
	}

	
	
	/**
	 * 
	 */
	




}
