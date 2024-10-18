package com.beIt.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import com.beIt.entities.Cash;
import com.beIt.entities.Parametre;
import com.beIt.entities.Partenaire;
import com.beIt.entities.PossibleValues;
import com.beIt.entities.ResponseParam;
import com.beIt.entities.Transaction;
import com.beIt.entities.TypeTransaction;
import com.beIt.repositories.CashRepository;
import com.beIt.repositories.PossibleValueRepository;
import com.beIt.repositories.ResponseParamRepository;
import com.beIt.repositories.TransactionRepository;
import com.beIt.repositories.TypeTransactionRepository;

import net.minidev.json.JSONObject;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transaction")
public class TransactionController {

	@Autowired
	TransactionRepository repo;
	
	@Autowired
	TypeTransactionRepository typeTransRepo;
	
	@Autowired
	ApiController apiControl;
	
	@Autowired
	CashController cashControl;
	
	
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
	
	
	@GetMapping("/list")
	public List<Transaction> getTransaction(){
		return repo.findAll();
	}
	
	@GetMapping("/paiement")
	public List<Transaction> getTransactionsByTypePaiement() {
	    return repo.findByTransactionTypePaiement();
	}

	@GetMapping("/remboursement")
	public List<Transaction> findByTransactionTypeRemboursement() {
	    return repo.findByTransactionTypeRemboursement();
	}

	
	@GetMapping("/{id}")
	public Transaction getTransactionById(@PathVariable(value="id") long id){
		Transaction u = null;
		Optional<Transaction> findItem = repo.findById(id);
		if(findItem.isPresent()) {
			u = findItem.get();
		}
		return u;
	}
	
	@GetMapping("/getByEventId/{idEvent}")
	public List<Transaction> getTransactionByEventId(@PathVariable(value="idEvent") long id){
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
	
	/**
	 * fonction permettant de retourner le chemin d'acces du parametre p 
	 * @param p
	 * @param path
	 * @return
	 */
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

		System.out.println("chemin "+p.getKey()+"  = " +chemin);
		
		return chemin;
	}
	
	/**
	 * elle permet de retourner la valeur d'un parametre
	 * @param par
	 * @param reponse
	 * @return
	 */
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
	
	
	@Transactional
	@Modifying
	@GetMapping("/getResponse/{id}")
	public Object getReponse(@PathVariable Long id)
	{
//		System.out.println("Transaction :"+ id);
		Transaction transaction = this.getTransactionById(id);
		Partenaire p = transaction.getPartenaire();
		
		Long idAction = (long)2;
		String methode = "GET";
		String managedEntity = "PAIEMENT";		
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
		
		transaction.setResponseEnquiry(enquiryResponse);
		this.updateTransaction(id, transaction);
		
		JSONObject response = new JSONObject();
		
		System.out.println("objects = "+objects);
		
		String valeur = this.getValue(parametre, objects);
//		String valeur = getValueFromJson(objects, parametre.getKey());
		System.out.println("valeur = "+ valeur);
		
		System.out.println("rp.getValue() = "+ rp.getValue());
		
		if(rp.getValue().equals(valeur))
		{
			this.updateStatut(transaction.getIdTransaction(), "TERMINEE");
			PossibleValues pv = pvRepo.getPossibleValue(valeur);
			response.put("status","Success");
			response.put("message", pv.getMessage());
			response.put("code", "BP0001");
		}
		else
		{
			System.out.println("NOK");
			this.updateStatut(transaction.getIdTransaction(), "ECHOUEE");
			for(PossibleValues pvs : rp.getPossible_values())
			{
				if(pvs.getValue().equals(valeur))
				{
					response.put("status","ECHOUEE");
					response.put("message", pvs.getMessage());
					response.put("code", "BP0002");
				}
			}
		}
		
		return response;
		
	}
	
	@PostMapping("/save")
	public Transaction addTransaction(@RequestBody Transaction p) {
						
		return repo.save(p);
	}
	
	@Transactional
	@Modifying
	@PostMapping("/saveTransaction/{idP}")
	public JSONObject saveTransaction(@PathVariable Long idP,@RequestBody Transaction transaction) {		
		Long idAction = (long) 3;
		String methode = "POST";
		String managedEntity = "PAIEMENT";
		

		Api api = apiControl.getApi(idP,methode, idAction, managedEntity);
//		System.out.println("url = "+api.getUrl());
		String baseUrl = partControl.get(idP).getBaseUrl();
		String url = baseUrl+api.getUrl();
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
		transaction = this.addTransaction(transaction);
		
		
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
				param.setValue(transaction.getLibelle_transaction());
				paramControl.update(param.getIdParametre(), param);
				break;
			case 2 :
				param.setValue(transaction.getNumero());
				paramControl.update(param.getIdParametre(), param);
				break;
			case 3 :
				param.setValue(String.valueOf(transaction.getMontant()));
				paramControl.update(param.getIdParametre(), param);
				break;
			case 4 :
				param.setValue(String.valueOf(transaction.getIdTransaction()));
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
			this.updateStatut(transaction.getIdTransaction(), "EN COURS");
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
		response.put("idTransaction", transaction.getIdTransaction());
		
		return response;
	}
	
	
	///////////////debut remboursement////////////////

	@GetMapping("/refundTransaction/{transactionId}")
	public JSONObject refundTransaction(@PathVariable Long transactionId) {
	    Long idAction = (long) 3;  // Action spécifique pour un remboursement
	    String methode = "POST";
	    String managedEntity = "REMBOURSEMENT";

	    // Récupérer la transaction existante
	    Transaction transaction = repo.findById(transactionId)
	            .orElseThrow(() -> new RuntimeException("Transaction non trouvée"));

	    Api api = apiControl.getApi(transaction.getPartenaire().getIdPartenaire(), methode, idAction, managedEntity);
	    String baseUrl = partControl.get(transaction.getPartenaire().getIdPartenaire()).getBaseUrl();
	    String url = baseUrl + api.getUrl();
	    System.out.println("URL ="+url);
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
		
		TypeTransaction refundTranType= this.typeTransRepo.findTypeRefund();
				
		transaction = this.updateTransactionRefund(transactionId, transaction, refundTranType);
		
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
				param.setValue(transaction.getLibelle_transaction());
				paramControl.update(param.getIdParametre(), param);
				break;
			case 2 :
				param.setValue(transaction.getNumero());
				paramControl.update(param.getIdParametre(), param);
				break;
			case 3 :
				param.setValue(String.valueOf(transaction.getMontant()));
				paramControl.update(param.getIdParametre(), param);
				break;
			case 4 :
				param.setValue(String.valueOf(transaction.getIdTransaction()));
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
		JSONObject object = sessionControl.getToken(transaction.getPartenaire().getIdPartenaire());
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
			this.updateStatut(transaction.getIdTransaction(), "EN COURS");
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
		response.put("idTransaction", transaction.getIdTransaction());
		
		return response;
	}

	/////////fin remboursement/////////
	
	/////////debut cash/////////
	

	@Autowired
	CashRepository repoCash;
	
	
		
	@Modifying
	@PostMapping("/saveCash/{idP}")
	public JSONObject saveCash(@PathVariable Long idP,@RequestBody Cash cash) {		
		Long idAction = (long) 3;
		String methode = "POST";
		String managedEntity = "CASHIN";
		

		Api api = apiControl.getApi(idP,methode, idAction, managedEntity);
//		System.out.println("url = "+api.getUrl());
		String baseUrl = partControl.get(idP).getBaseUrl();
		String url = baseUrl+api.getUrl();
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
		cash = cashControl.createCash(cash);
		
		
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
				param.setValue(cash.getLibelle());
				paramControl.update(param.getIdParametre(), param);
				break;
			case 2 :
				param.setValue(cash.getNumero());
				paramControl.update(param.getIdParametre(), param);
				break;
			case 3 :
				param.setValue(String.valueOf(cash.getMontant()));
				paramControl.update(param.getIdParametre(), param);
				break;
			case 4 :
				param.setValue(String.valueOf(cash.getIdCash()));
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
			this.updateStatut(cash.getIdCash(), "EN COURS");
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
		response.put("idTransaction", cash.getIdCash());
		
		return response;
	}
	
		
	/////////fin cash/////////
	
	@Modifying
	private void updateStatut(long idTransaction,  String newStatut) {
		// TODO Auto-generated method stub
		this.repo.updateStatut(idTransaction, newStatut);
		
	}

	@DeleteMapping("/{id}")
	public void deleteTransactionById(@PathVariable Long id) {
		repo.deleteById(id);
	}
	
	
	@Modifying
	public Transaction updateTransactionRefund(@PathVariable("id") final Long id, @RequestBody Transaction transaction, @RequestBody TypeTransaction typeTransaction) {
		Optional<Transaction> e = repo.findById(id) ;
		if(e.isPresent()) {
			Transaction currentTransaction = e.get();
			
			
			TypeTransaction type = typeTransaction;
			if(type != null) {
				currentTransaction.setTypeTransaction(type);
			}
			
			currentTransaction = repo.save(currentTransaction);
			return currentTransaction;
		} else {
			return null;
		}
	}
	
	
	@PutMapping("/{id}")
	public Transaction updateTransaction(@PathVariable("id") final Long id, @RequestBody Transaction transaction) {
		Optional<Transaction> e = repo.findById(id) ;
		if(e.isPresent()) {
			Transaction currentTransaction = e.get();
			
			String code = transaction.getCode() ;
			if(code != null) {
				currentTransaction.setCode(code);
			}
			
			String numero = transaction.getNumero();
			if(numero != null) {
				currentTransaction.setNumero(numero);
			}
			
			String nom = transaction.getNom();
			if(nom != null) {
				currentTransaction.setNom(nom);
			}
			
			String libelle = transaction.getLibelle_transaction();
			if(libelle != null) {
				currentTransaction.setLibelle_transaction(libelle);
			}
			
			int montant = transaction.getMontant();
			if(montant != 0) {
				currentTransaction.setMontant(montant);
			}
			
			
			String prenom = transaction.getPrenom();
			if(prenom != null) {
				currentTransaction.setPrenom(prenom);
			}
			
			TypeTransaction type = transaction.getTypeTransaction();
			if(type != null) {
				currentTransaction.setTypeTransaction(type);
			}
			
			Partenaire part = transaction.getPartenaire();
			if(part != null) {
				currentTransaction.setPartenaire(part);
			}
			
			if(transaction.getResponseEnquiry() != null && !transaction.getResponseEnquiry().isEmpty()) {
				currentTransaction.setResponseEnquiry(transaction.getResponseEnquiry());
			}
			
			currentTransaction = repo.save(currentTransaction);
			return currentTransaction;
		} else {
			return null;
		}
	}
}
