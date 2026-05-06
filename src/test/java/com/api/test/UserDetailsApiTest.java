package com.api.test;

import static  io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;


import static com.api.utils.AuthTokenProvider.*;

import static  com.api.utils.ConfigManager2.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;



public class UserDetailsApiTest {
	
	
	@Test
	public void userDetailsAPITest() throws IOException {
		
		Header header= new Header("Authorization",getToken(Role.FD));
		
		given().baseUri(getProperty("BASE_URI")).contentType(ContentType.JSON).and().accept(ContentType.JSON).and().header(header).
		when().get("userdetails").then().log().all().statusCode(200).and().time(lessThan(1500l)).
		and().body("message",equalTo("Success")).body(matchesJsonSchemaInClasspath("response-schema/userDetailsSchema.json"));
	}

}
