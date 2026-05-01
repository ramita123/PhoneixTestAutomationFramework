package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {
	
	@Test
	public void loginApiTest() {

		
		UserCredentials userCredentials=new UserCredentials("iamfd","password");
		
		given().baseUri("http://64.227.160.186:9000/v1").and().contentType(ContentType.JSON).and().accept(ContentType.JSON).
		and().body(userCredentials).
		log().uri().and().log().headers().and().log().method().and().
		log().body().when().post("/login").then().log().all().statusCode(200).and().time(lessThan(1000l))
		.and().body("message", equalTo("Success")).body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
	}

}
