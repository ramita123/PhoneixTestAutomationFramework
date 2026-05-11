package com.api.test;

import static  io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

import static com.api.utils.AuthTokenProvider.*;

import static  com.api.utils.ConfigManager2.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;



public class UserDetailsApiTest {
	
	
	@Test
	public void userDetailsAPITest() throws IOException {
		
		given().spec(SpecUtil.requestSpecWithAuth(Role.FD)).
		when().get("userdetails").then().spec(SpecUtil.responseSpec_OK())
		.body("message",equalTo("Success")).body(matchesJsonSchemaInClasspath("response-schema/userDetailsSchema.json"));
	}

}
