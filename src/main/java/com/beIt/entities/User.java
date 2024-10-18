package com.beIt.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;

@Entity(name = "utilisateur")
@Data 
@Table(name = "utilisateur")
public class User extends Auditable<String>{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
	@Column(unique=true)
    private String username;
    
    @Column(unique=true)
    private String email;
    
    private String telephone;
    private String password;
    private String nom;
    private Boolean statut=false;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
    @JoinColumn(name ="profile_id")
	Profile profile_user;
    
  

  
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	



	
}

