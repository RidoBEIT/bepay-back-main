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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "reponse_param")
@Data
public class ResponseParam {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
	protected long idReponseParam;
	
	@Column
	protected String key;
		
	@Column
	protected String value;
	
	@Column
	protected int idApi;
	
	@JsonManagedReference(value="possible_values")
    @OneToMany(mappedBy="response_param", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<PossibleValues> possible_values;

	@Override
	public String toString() {
		return "ResponseParam [idReponseParam=" + idReponseParam + ", key=" + key + ", value=" + value + "]";
	}

}
