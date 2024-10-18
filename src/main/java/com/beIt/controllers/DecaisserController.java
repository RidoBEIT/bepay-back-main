package com.beIt.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.beIt.entities.Api;
import com.beIt.entities.Decaisser;
import com.beIt.entities.Parametre;
import com.beIt.entities.Partenaire;
import com.beIt.entities.PossibleValues;
import com.beIt.entities.ResponseParam;
import com.beIt.entities.Transaction;
import com.beIt.entities.TypeTransaction;
import com.beIt.repositories.ApiRepository;
import com.beIt.repositories.DecaisserRepository;
import com.beIt.repositories.ParametreRepository;
import com.beIt.repositories.PossibleValueRepository;
import com.beIt.repositories.ResponseParamRepository;
import com.beIt.repositories.TransactionRepository;

import net.minidev.json.JSONObject;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/decaisser")
public class DecaisserController {

	@Autowired
	DecaisserRepository repo;
	@Autowired
	ApiController apiControl;
	@Autowired
	ParamController paramControl;
	@Autowired
	SessionController sessionControl;
	@Autowired
	private RestTemplate template;
	@Autowired
	PartenaireController partControl;
	@Autowired
	private PossibleValueRepository pvRepo; 
	
	@Autowired
	ResponseParamRepository rpRepository;
	
	@Autowired
	ApiRepository apiRepo;
	
	@Autowired
	DecaisserRepository decaisserRepo;
	
	
	@GetMapping("/list")
	public List<Decaisser> getDecaisser(){
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
	public Decaisser getDecaisserById(@PathVariable(value="id") long id){
		Decaisser u = null;
		Optional<Decaisser> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			u = findItem.get();
		}
		return u;
	}
	
	@GetMapping("/getByEventId/{idEvent}")
	public List<Decaisser> getDecaisserByEventId(@PathVariable(value="idEvent") long id){
		return repo.findByEventId(id);
	}
	
	@GetMapping("/getJsonObject")
	public JSONObject getJSONObject(@RequestBody Parametre param) {
			
		//System.out.println(param.getKey() +" = " + param.getHas_children());
			if(param.getHas_children()==false) {
				JSONObject object = new JSONObject();
				object.put(param.getKey(), param.getValue());
				//System.out.println("object = "+ object);
				
				return object;
			}
			else {
				List<Parametre> childs = paramControl.getBodyChildren(param.getIdParametre());
				JSONObject object =new JSONObject();
				for(Parametre p:childs) {
					if(p.getHas_children()==false) {
					object.put(p.getKey(),p.getValue());
					//System.out.println("Param = "+ p.getKey() + " " + p.getValue());
					}
					else
					{
						object.put(p.getKey(), this.getJSONObject(p));
					}
				}
					
				
				return object;
			}
		
		//return new JSONObject();
		
		
		
	}
	@GetMapping("/getPath")
	public List<String> getPath(@RequestBody Parametre p,List<String> path) {
		
		for(int i=p.getNiveau();i>0;i--) {

			 if(i== p.getNiveau() && p.getParent()!=null)
			 {
				Parametre parent = paramControl.get(p.getParent());
				path.add(parent.getKey());
				 path = getPath(parent,path);
			}

		}
		
		return path;
	}
	
	@GetMapping("/chemin")
	public String getChemin(@RequestBody Parametre p)
	{
		List<String> path= new ArrayList<String>();
		path =this.getPath(p,path);
		//System.out.println("path = " +path);
		String chemin = "";
		for(int i=path.size()-1;i>-1; i--) {
			chemin+=path.get(i)+".";
		}
		chemin+=p.getKey();

		//System.out.println("chemin "+p.getKey()+"  = " +chemin);
		
		return chemin;
	}
	
	public String getValue (Parametre par, JSONObject reponse)
	{
		List<String> list = new ArrayList<String>();
		list = this.getPath(par, list);
		JSONObject object = reponse;
		String chemin = "";
		for(int i=list.size()-1;i>-1; i--) {
			Object ob = object.get(list.get(i));
			object = new JSONObject((Map<String, ?>) ob);
			//object =  object.get(list.get(i));
		}
		//System.out.println("valeur = "+par.getKey()+ " = " + object);
		
		return object.getAsString(par.getKey());
	}
	
	//@Decaisseral
	@Modifying
	@GetMapping("/getResponse/{id}")
	public Object getReponse(@PathVariable Long id)
	{
//		System.out.println("Decaisser :"+ id);
		Decaisser decaisser = this.getDecaisserById(id);
		Partenaire p = decaisser.getPartenaire();
		
		Long idAction = (long)1;
		String methode = "GET";
		String managedEntity = "DECAISSEMENT";		
		Api api = apiControl.getApi(p.getIdPartenaire(),methode, idAction, managedEntity);
		
		
		String baseUrl = partControl.get(p.getIdPartenaire()).getBaseUrl();
		String url = baseUrl+api.getUrl()+"/"+id;
//		System.out.println("url = "+ url);
		
		List<Parametre> headersParam = new ArrayList<Parametre>();
		headersParam = paramControl.getParamByApiAndType(api.getIdApi(), "Header");
		
		JSONObject object = sessionControl.getToken(p.getIdPartenaire());
		String token = object.getAsString("token");
		String type = object.getAsString("type");
		
		ResponseParam rp = rpRepository.getOneByApi(api.getIdApi());
		System.out.println("rp = "+ rp);
		
		
		Parametre parametre = paramControl.getParamByApiAndTypeAndOrdre(api.getIdApi(),2, 1);
		
		System.out.println("parametre = "+ parametre);
		
		HttpHeaders headers = new HttpHeaders();
		
		for(Parametre param : headersParam )
		{
			
			int ordre = param.getOrdre();
			switch(ordre)
			{
			case 1 :
				param.setValue(type + " "+ token);
				paramControl.update(param.getIdParametre(), param);
				break;
			
			
			}
			System.out.println("headerParam = "+param.getKey() + " " + param.getValue());
			
			headers.add(param.getKey(), param.getValue());
			
		}
		
		HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(headers);
		
		ResponseEntity<JSONObject> responseEntity = template.exchange(url,  HttpMethod.GET, entity, JSONObject.class);
		
		JSONObject objects = responseEntity.getBody();
		
		String enquiryResponse = objects.toString();
		
		decaisser.setResponseEnquiry(enquiryResponse);
		this.updateDecaisser(id, decaisser);
		
		JSONObject response = new JSONObject();
		
		System.out.println("objects = "+objects);
		
		String valeur = this.getValue(parametre, objects);
		System.out.println("valeur = "+ valeur);
		
		System.out.println("rp.getValue() = "+ rp.getValue());
		
		if(rp.getValue().equals(valeur))
		{
			this.updateStatut(decaisser.getIdDecaisser(), "TERMINEE");
			PossibleValues pv = pvRepo.getPossibleValue(valeur);
			response.put("status","Success");
			response.put("message", pv.getMessage());
			response.put("code", "BP0002");
		}
		else
		{
			System.out.println("NOK");
			for(PossibleValues pvs : rp.getPossible_values())
			{
				
			}
		}
		
		return response;
		
	}
	
	@PostMapping("/save")
	public Decaisser addDecaisser(@RequestBody Decaisser p) {
						
		return repo.save(p);
	}
	
	@Transactional
	@Modifying
	@PostMapping("/sendMoney/{idP}")
	public JSONObject saveTransaction(@PathVariable Long idP,@RequestBody Decaisser decaisser) {		
		Long idAction = (long) 3;
		String methode = "POST";
		String managedEntity = "DECAISSEMENT";
		

		Api api = apiControl.getApi(idP,methode, idAction, managedEntity);
		String baseUrl = partControl.get(idP).getBaseUrl();
		String url = baseUrl+api.getUrl();
		System.out.println("url = "+url);
		List<Parametre> headersParam = new ArrayList<Parametre>();
		List<Parametre> bodyParam = new ArrayList<Parametre>();
		List<Parametre> bodyParent = new ArrayList<Parametre>();
		List<Parametre> responseParam = new ArrayList<Parametre>();
		List<Parametre> bodyNPNC = new ArrayList<Parametre>();
		headersParam = paramControl.getParamByApiAndType(api.getIdApi(), "Header");
		bodyParam = paramControl.getParamByApiAndType(api.getIdApi(), "RequestBody");
		bodyParent = paramControl.getBodyParent(api.getIdApi());
		bodyNPNC =  paramControl.getBodyNoParentNoChildren(api.getIdApi());
		responseParam = paramControl.getParamByApiAndType(api.getIdApi(), "Response");
		ResponseParam rp = api.getResponseParam();
		
		
		//On sauvegarde la transaction en locale
		decaisser = this.addDecaisser(decaisser);
		
		
//		System.out.println("rp = "+ rp);
		Parametre parametre = paramControl.getParamByApiAndTypeAndOrdre(api.getIdApi(),2, 1);
		JSONObject body =new JSONObject();
		for(Parametre param : bodyParam )
		{
			//System.out.println("bodyParam = "+param.getKey() + " " +param.getValue());
			int ordre = param.getOrdre();
			switch(ordre)
			{
			case 1 :
				param.setValue(decaisser.getLibelle_decaisser());
				paramControl.update(param.getIdParametre(), param);
				break;
			case 2 :
				param.setValue(decaisser.getNumero());
				paramControl.update(param.getIdParametre(), param);
				break;
			case 3 :
				param.setValue(String.valueOf(decaisser.getMontant()));
				paramControl.update(param.getIdParametre(), param);
				break;
			case 4 :
				param.setValue(String.valueOf(decaisser.getIdDecaisser()));
				paramControl.update(param.getIdParametre(), param);
				break;
			}
	
		}
		
		JSONObject requestBody = new JSONObject();
		// Ajout des paramètres qui ne sont ni parent ni enfants
		for(Parametre param:bodyNPNC) {
			requestBody.put(param.getKey(),param.getValue());
		}
		//Ajout des autre paramètre
		for(Parametre pa : bodyParent)
		{
			if(pa.getParent()==null)
			{
			requestBody.put(pa.getKey(), this.getJSONObject(pa));
			}
		}
		
		HttpHeaders headers = new HttpHeaders();
		JSONObject object = sessionControl.getToken(idP);
		String token = object.getAsString("token");
		String type = object.getAsString("type");
		
		//Ajout du header token
		for(Parametre param : headersParam )
		{
			//System.out.println("bodyParam = "+param.getKey());
			int ordre = param.getOrdre();
			switch(ordre)
			{
			case 1 :
				param.setValue(type + " "+ token);
				paramControl.update(param.getIdParametre(), param);
				break;
			
			
			}
			
			headers.add(param.getKey(), param.getValue());
			
		}
		
		
//		System.out.println("requestBody = "+requestBody);
		
		HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(requestBody,headers);
//		System.out.println("entity = "+entity);
		
		ResponseEntity<JSONObject> responseEntity = template.postForEntity(url, entity, JSONObject.class);
		//Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
		System.out.println("reponseEntity = "+responseEntity);
		JSONObject objects = responseEntity.getBody();
		JSONObject response = new JSONObject();
		
//		System.out.println("objects = "+objects);
		
		String valeur = this.getValue(parametre, objects);
//		System.out.println("getValue = " + rp.getValue());
//		System.out.println("valeur = " + valeur);

		if(rp.getValue().equals(valeur))
		{
			this.updateStatut(decaisser.getIdDecaisser(), "EN COURS");
			PossibleValues pv = pvRepo.getPossibleValue(valeur);
			response.put("status","Success");
			response.put("message", pv.getMessage());
			response.put("code", "BP0001");
		}
		else
		{
			for(PossibleValues pvs : rp.getPossible_values())
			{
				
			}
		}
		response.put("idTransaction", decaisser.getIdDecaisser());
		
		return response;
	}
////////////////////////////////////////////////////////////////////////////
	

	@Modifying
	private void updateStatut(long idDecaisser,  String newStatut) {
		// TODO Auto-generated method stub
		this.repo.updateStatut(idDecaisser, newStatut);
		
	}

	@DeleteMapping("/{id}")
	public void deleteDecaisserById(@PathVariable Long id) {
		repo.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Decaisser updateDecaisser(@PathVariable("id") final Long id, @RequestBody Decaisser decaisser) {
		Optional<Decaisser> e = repo.findById(id) ;
		if(e.isPresent()) {
			Decaisser currentDecaisser = e.get();
			
			
			String numero = decaisser.getNumero();
			if(numero != null) {
				currentDecaisser.setNumero(numero);
			}
			
			String nom = decaisser.getNom();
			if(nom != null) {
				currentDecaisser.setNom(nom);
			}
			
			String libelle = decaisser.getLibelle_decaisser();
			if(libelle != null) {
				currentDecaisser.setLibelle_decaisser(libelle);
			}
			
			
			String prenom = decaisser.getPrenom();
			if(prenom != null) {
				currentDecaisser.setPrenom(prenom);
			}
			
			TypeTransaction type = decaisser.getTypeTransaction();
			if(type != null) {
				currentDecaisser.setTypeTransaction(type);
			}
			
			if(decaisser.getResponseEnquiry() != null && !decaisser.getResponseEnquiry().isEmpty()) {
				currentDecaisser.setResponseEnquiry(decaisser.getResponseEnquiry());
			}
			
			currentDecaisser = repo.save(currentDecaisser);
			return currentDecaisser;
		} else {
			return null;
		}
	}
}
