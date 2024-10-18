package com.beIt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data 
@Table(	name = "typeAction")
public class TypeAction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeAction;

    @Column(name="libelle_typeAction")
    private String libelle_typeAction;

}
