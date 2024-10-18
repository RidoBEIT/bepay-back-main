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
@Table(	name = "typeReponse")
public class TypeReponse {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeReponse;

    @Column(name="libelle_typeReponse")
    private String libelle_typeReponse;
    
    @Column(name="type")
    private int type;

	@Override
	public String toString() {
		return "TypeReponse [idTypeReponse=" + idTypeReponse + ", libelle_typeReponse=" + libelle_typeReponse + "]";
	}
}
