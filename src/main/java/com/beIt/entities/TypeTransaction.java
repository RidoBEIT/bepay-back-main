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
@Table(	name = "typeTransaction")
public class TypeTransaction {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeTransaction;

    @Column(name="libelle_typeTransaction")
    private String libelle_typeTransaction;
}
