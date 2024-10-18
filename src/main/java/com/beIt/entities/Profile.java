package com.beIt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="profile")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Profile extends Auditable<String>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id_profile;
	@Column
	String nom_profile;
	
	public Profile() {
		super();
	}
	public Profile(Long id_profile, String nom_profile) {
	
		super();
		this.id_profile=id_profile;
		this.nom_profile = nom_profile;
		
	}
	
}

