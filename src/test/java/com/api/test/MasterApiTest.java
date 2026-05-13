package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constant.Role;
import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterApiTest {
	
	@Test(description="verify master api giving correct response",groups= {"api","regression","smoke"})
	public void masterApiTest() throws IOException {
		
		given().spec(requestSpecWithAuth(Role.FD)).
		then().spec(responseSpec_OK()).body("message", equalTo("Success")).
		body("data",notNullValue()).body("data",hasKey("mst_oem"))
		.body("data",hasKey("mst_model"))
		.body("data",hasKey("mst_oem")).body("$",hasKey("message")).body("data.mst_oem.size()",equalTo(2)).
		body("data.mst_oem.id",everyItem(notNullValue())).
		body(matchesJsonSchemaInClasspath("response-schema/masterApiResponseSchema.json"));
		//In Rest Assured, this is a Groovy GPath expression
		
	}
	
	@Test(description="verify the api is returning correct status code for invalid auth",groups= {"api","regression","smoke"})
	public void invalidTokenMasterApi() throws IOException {
		given().spec(requestSpec()).
		when().post("/master").
		then().spec(responseSpec_TEXT(401));
	}

}
