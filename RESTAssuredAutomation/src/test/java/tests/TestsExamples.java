package tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class TestsExamples {
	@Test
	public void test_1() {
		Response response = get("https://reqres.in/api/users?page=2");
		
		int statusCode = response.getStatusCode();
		System.out.println("StatusCode: "+statusCode);
		System.out.println("Tempo: "+response.getTime());
		System.out.println("Body: "+response.getBody().asString());
		System.out.println("StatusLine: "+response.getStatusLine());
		System.out.println("Header: "+response.getHeader("content-type"));
		
		
		Assert.assertEquals(statusCode, 201);
	}
	
	@Test
	public void test_2() {
		
		baseURI = "https://reqres.in/api";
		
		given().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data[1].id", equalTo(8)).
			log().all();
		
	}
}
