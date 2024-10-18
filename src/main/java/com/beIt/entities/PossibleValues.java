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
@Table(name = "possible_values")
@Data
public class PossibleValues {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
	protected long idPossibleValue;
	
	@Column
	protected String message;
		
	@Column
	protected String value;
	
	@JsonBackReference(value="possible_values")
	@ManyToOne
	protected ResponseParam response_param;

}
