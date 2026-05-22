package com.api.test;

import static  io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;
import static com.api.utils.SpecUtil.*;

import static com.api.utils.AuthTokenProvider.*;

import static  com.api.utils.ConfigManager2.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;



public class UserDetailsApiTest {
	
	
	@Test(description="verify user details response shown correctly and it belongs to the group ",groups= {"api","regression","smoke"})
	public void userDetailsAPITest() throws IOException {
		
		given().spec(requestSpecWithAuth(Role.FD)).
		when().get("userdetails").then().spec(responseSpec_OK())
		.body("message",equalTo("Success")).body(matchesJsonSchemaInClasspath("response-schema/userDetailsSchema.json"));
	}

}
