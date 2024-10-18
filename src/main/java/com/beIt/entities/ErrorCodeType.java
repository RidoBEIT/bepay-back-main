package com.beIt.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data 
@Table(	name = "errorCodeType")
public class ErrorCodeType {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idErrorCodeType;

    @Column(name="libelle_type_error")
    private String libelle_type_error;
    
    @Column(name="description")
    private String description;
    
    @ManyToOne
    protected Partenaire partenaire;
    
    @JsonManagedReference(value="possible_values")
    @OneToMany(mappedBy="errorCodeType", cascade = CascadeType.ALL, orphanRemoval = true)
    protected Collection<ErrorCode> possible_values;
}
