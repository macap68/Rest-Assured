package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchDeleteExamples {
	@Test
	public void testPut() {
		
		JSONObject requestJO = new JSONObject();
		System.out.println("PUT in formato json utilizzando JSONObject");
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
			put("/users/2").
		then().			
			statusCode(200).
			log().all();
		
		
	}
	@Test
	public void testPatch() {
		
		JSONObject requestJO = new JSONObject();
		System.out.println("PATCH in formato json utilizzando JSONObject");
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
			patch("/users/2").
		then().			
			statusCode(200).
			log().all();
		
		
	}
	@Test
	public void testDelete() {
				
		baseURI= "https://reqres.in/api";

		when().
			delete("/users/2").
		then().			
			statusCode(204).
			log().all();
		
		
	}
}
