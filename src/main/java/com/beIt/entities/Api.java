
package com.beIt.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Entity
@Data 
@Table(	name = "api")
//@JsonIdentityInfo(
//		  generator = ObjectIdGenerators.PropertyGenerator.class, 
//		  property = "idApi")
public class Api extends Auditable<String> {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApi;

    @Column(name="libelle")
    private String libelle;
    
    @Column(name="description")
    private String description;
    
    @Column(name="url")
    private String url;
    
    @Column(name="typeRetour")
    private String typeRetour;
    
    @Column(name="methode")
    private String methode;
    
    @Column(name="managedEntity")
    private String managedEntity;
    
    @JsonManagedReference(value="parametres")
    @OneToMany(mappedBy="api", cascade =CascadeType.REMOVE, orphanRemoval = true)
	private Collection<Parametre> parametres;
    
    @OneToOne
    @JoinColumn(referencedColumnName = "idReponseParam")
	private ResponseParam responseParam;
    
    @ManyToOne
    private TypeAction action;
    
    @ManyToOne
    private TypeReponse reponse;
   
    @ManyToOne
    private Partenaire partenaire;


	/**
	 * 
	 */
	public Api() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Api(Long idApi, String libelle, String description, String url, String typeRetour, String methode,
			String managedEntity, Collection<Parametre> parametres, TypeAction action, Partenaire compagnie) {
		super();
		this.idApi = idApi;
		this.libelle = libelle;
		this.description = description;
		this.url = url;
		this.typeRetour = typeRetour;
		this.methode = methode;
		this.managedEntity = managedEntity;
		this.parametres = parametres;
		this.action = action;
		this.partenaire = compagnie;
	}

	@Override
	public String toString() {
		return "Api [idApi=" + idApi + ", libelle=" + libelle + ", description=" + description + ", url=" + url
				+ ", typeRetour=" + typeRetour + ", methode=" + methode + ", managedEntity=" + managedEntity
				+ ", parametres=" + parametres + ", action=" + action + ", partenaire=" + partenaire + "]";
	}

	
	



	
}
