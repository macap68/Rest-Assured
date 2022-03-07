package tests;


import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetAndPostExamples {
	@Test
	public void testGet() {
		baseURI= "https://reqres.in/api";
		given().
			get("/users?page=2").
		then().statusCode(200).
			body("data[4].first_name", equalTo("George")).
			body("data.first_name",hasItems("George","Rachel"));
	}
	
	@Test
	public void testPost() {
		//Utilizzo una collection per il momento per contenere i valori da trasformare in json 
		Map<String, Object> map = new HashMap<String, Object>();
		//Potrei utilizzare il carattere di escape \" per mostrare i doppi apici nell'output e renderlo Json compatibile
		//Ma è meglio utilizzare delle librerie appropiate per evitare problemi es. gson e json.simple (google), jackson o json 
		//map.put("\"name\"", "Raghav");
		map.put("name", "Raghav");
		map.put("job", "Teacher");
		System.out.println("POST in un formato non perfettamente formattato in json");
		System.out.println(map);
		System.out.println();
		
		System.out.println("POST in formato json utilizzando JSONObject e l'utilizzo della collection Map");
		JSONObject requestMap = new JSONObject(map);
		System.out.println(requestMap.toJSONString());
		System.out.println();
		
		//Posso evitare di utilizzare la collection utilizzando direttamente il JSONObject
		JSONObject requestJO = new JSONObject();
		System.out.println("POST in formato json utilizzando JSONObject senza l'utilizzo della collection Map");
		requestJO.put("name", "Raghav");
		requestJO.put("job", "Teacher");
		System.out.println(requestJO.toJSONString());
		System.out.println();
		
		baseURI= "https://reqres.in/api";
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(requestJO.toJSONString()).
		when().
			post("/users").
		then().			
			statusCode(201).
			log().all();
		
		
	}
	
}
