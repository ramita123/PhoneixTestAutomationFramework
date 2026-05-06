package com.api.test;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.utils.ConfigManager2.*;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterApiTest {
	
	@Test
	public void masterApiTest() throws IOException {
		
		given().
		baseUri(getProperty("BASE_URI")).
		contentType("").
		and().
		header("Authorization",getToken(Role.FD)).
		when().post("/master").
		then().log().all().
		statusCode(200).time(lessThan(1000l)).and().body("message", equalTo("Success")).
		body("data",notNullValue()).body("data",hasKey("mst_oem"))
		.body("data",hasKey("mst_model"))
		.body("data",hasKey("mst_oem")).body("$",hasKey("message")).body("data.mst_oem.size()",equalTo(2)).
		body("data.mst_oem.id",Matchers.everyItem(notNullValue())).
		body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/masterApiResponseSchema.json"));
		//In Rest Assured, this is a Groovy GPath expression
		
	}
	
	@Test
	public void invalidTokenMasterApi() throws IOException {
		given().
		baseUri(getProperty("BASE_URI")).
		contentType("").
		when().post("/master").
		then().log().all().
		statusCode(401);
	}

}
