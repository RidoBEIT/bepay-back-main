package com.beIt.controllers;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.beIt.entities.Api;
import com.beIt.entities.Parametre;
import com.beIt.entities.Partenaire;
import com.beIt.entities.Session;
import com.beIt.entities.TypeReponse;

import net.minidev.json.JSONObject;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("")
public class AuthenticateController {

	@Autowired 
	ApiController apiControl;
	@Autowired
	private RestTemplate template;
	
	@Autowired
	PartenaireController partControl;
	
	@Autowired 
	SessionController sessionControl;
	
	@Autowired
	ParamController paramControl;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	Date currentDate = new Date((new java.util.Date()).getTime());
	//LocalDateTime currentTime = LocalDateTime.parse(time);
	
	@PostMapping("/authenticate/{idPartenaire}")
	public JSONObject authenticate(@PathVariable Long idPartenaire) {
		 
		sessionControl.unSetCurrentSession();
		Long idA = (long)3;
		String mtd = "POST";
		String me = "AUTHENTIFICATION";
		Api api = apiControl.getApi(idPartenaire,mtd, idA, me);
		TypeReponse reponse = api.getReponse();
//		System.out.println("reponse "+reponse);
		String baseUrl = partControl.get(idPartenaire).getBaseUrl();
		String url = baseUrl+api.getUrl();
//		System.out.println("url "+url);
		List<Parametre> headersParam = new ArrayList();
		List<Parametre> bodyParam = new ArrayList();
		Partenaire partenaire = partControl.get(idPartenaire);
		String login = partenaire.getLogin();
		String password = partenaire.getPassword();
		
		headersParam = paramControl.getParamByApiAndType(api.getIdApi(), "Header");
		
		bodyParam = paramControl.getParamByApiAndType(api.getIdApi(), "RequestBody");
		
		HttpHeaders headers = new HttpHeaders();
		for(Parametre param : headersParam)
		{
			headers.add(param.getKey(), param.getValue());
		}
		
		JSONObject body = new JSONObject();
		
		for(Parametre param : bodyParam)
		{
			body.put(param.getKey(), param.getValue());
			int ordre = param.getOrdre();
			switch(ordre)
			{
			case 1 :
				body.put(param.getKey(), login);
				break;
			case 2 :
				body.put(param.getKey(), password);
				break;
			
			}
			
			
		}
//		System.out.println("body "+body);
//
//		System.out.println("header "+headers);


//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(body,headers);
		
		ResponseEntity<JSONObject> responseEntity = template.exchange(url,  HttpMethod.POST, entity, JSONObject.class);
		
		JSONObject objects = responseEntity.getBody();
//        System.out.println(reponse);

		if(reponse.getType()==3)
			
		{           

			LocalDateTime time = LocalDateTime.now();
			List<Parametre> reponses = new ArrayList();
			reponses = paramControl.getParamByApiAndType(api.getIdApi(), "Response");
			String token = objects.getAsString(reponses.get(0).getKey());
			int expire = (int) objects.get(reponses.get(1).getKey());
			String token_type = objects.getAsString(reponses.get(2).getKey());
			LocalDateTime dt1 = LocalDateTime.parse(time.toString());
	        LocalDateTime dt2=dt1.plusSeconds(expire);
	        //System.out.println("dt1");

	        String tokenDate = dt2.toString()+"+0000";
	        Session session = new Session();
			session.setPartenaire(partenaire);
			session.setExpireDate(tokenDate);
			session.setToken(token);
			session.setToken_type(token_type);
			session = sessionControl.create(session);
			
		}
		
		return objects;
		
		//return new JSONObject();
	}
	
//	@GetMapping("/authenticate/{idCo}")
//	public HttpHeaders authenticat(@PathVariable Long idCo) {
//		//sessionControl.unSetCurrentSession();
//		Long idA = (long)3;
//		String mtd = "POST";
//		String me = "AUTHENTIFICATION";
//		Api api = apiControl.getApi(idCo,mtd, idA, me);
//		String baseUrl = partControl.get(idCo).getBaseUrl();
//		String url = baseUrl+api.getUrl();
//		//System.out.println("url "+url);
//		List<Parametre> headersParam = new ArrayList();
//		
//		List<Parametre> bodyParam = new ArrayList();
//		
//		headersParam = paramControl.getParamByApiAndType(api.getIdApi(), "Header");
//		
//		bodyParam = paramControl.getParamByApiAndType(api.getIdApi(), "RequestBody");
//		
//		HttpHeaders headers = new HttpHeaders();
//		for(Parametre param : headersParam)
//		{
//			headers.add(param.getKey(), param.getValue());
//		}	
//		return headers;
//	}
	@GetMapping("/expire/{tokenDate}")
	 public Boolean isExpired (@PathVariable String tokenDate)
	 {
		LocalDateTime time = LocalDateTime.now();

		String[] table = tokenDate.split("\\+",2);
		String table0 = table[0];
		String table1 = table[1];
		//System.out.println("table0 " + table0 + " table1 : " + table1);


		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		LocalDateTime date = LocalDateTime.parse(table0) ;
		Timestamp ts1 = Timestamp.valueOf(date);
		Timestamp ts2 = Timestamp.valueOf(time);
		
////		
//		System.out.println("time " + time);
//		System.out.println("date " + date);
//		System.out.println("Localdatetime " + ts2);
//		System.out.println("tokenDate " + tokenDate);

		
//		 String[] table = tokenDate.split("T");
//		LocalDateTime date = LocalDateTime.parse(tokenDate) ;
		
		//Date date = Date.valueOf(tokenDate);
		 int result = ts2.compareTo(ts1);
		 if(result<0) {
			// System.out.println("result = "+result);
			 return false;
			 
		 }
		 else {
			// System.out.println("result = "+result);

			 return true;
		 }
		
	 }
	
	
	
	 
	 
}
