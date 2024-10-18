/**
 * 
 */
package com.beIt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
/**
 * @author Amadou Ali Ousseini
 *
 */
@Entity
@Data 
@Table(	name = "parametre")
//@JsonIdentityInfo(
//		  generator = ObjectIdGenerators.PropertyGenerator.class, 
//		  property = "idParametre")
public class Parametre extends Auditable<String>{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParametre;

    @Column(name="value")
    private String value;
    
    @Column(name="key")
    private String key;
    
    @Column(name="ordre")
    private int ordre;
    
    @Column(name="niveau")
    private int niveau = 0;
    
    @Column(name="type")
    private String type;
    
    
    //
    @Column(name="code_type")
    private int code_type;
    
    @Column(name="description")
    private String description;
    
    @Column(name="parent_id")
    private Long parent;
    
    @Column(name="has_children")
    private Boolean has_children = false;
    
    @JsonBackReference(value="parametres")
    @ManyToOne
	private Api api;

	/**
	 * 
	 */
	public Parametre() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param idParametre
	 * @param value
	 * @param key
	 * @param api
	 */
	public Parametre(String value, String key, Api api, int ordre) {
		super();
		this.value = value;
		this.key = key;
		this.api = api;
		this.ordre = ordre;
	}


	@Override
	public String toString() {
		return "Parametre [idParametre=" + idParametre + ", value=" + value + ", key=" + key + ", ordre=" + ordre
				+ ", niveau=" + niveau + ", type=" + type + ", code_type=" + code_type + ", description=" + description
				+ ", has_children=" + has_children + "]";
	}
	
	
	
    
}
