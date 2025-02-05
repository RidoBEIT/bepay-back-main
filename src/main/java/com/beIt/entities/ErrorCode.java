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

@Entity
@Data 
@Table(	name = "errorCode")
public class ErrorCode {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idErrorCode;

    @Column(name="code")
    private String code;
    
    @Column(name="description")
    private String description;
    
    @JsonBackReference(value="possible_values")
    @ManyToOne
	protected ErrorCodeType errorCodeType;
}
