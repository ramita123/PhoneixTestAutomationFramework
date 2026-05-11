package com.api.test;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.utils.ConfigManager2.*;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterApiTest {
	
	@Test
	public void masterApiTest() throws IOException {
		
		given().spec(SpecUtil.requestSpecWithAuth(Role.FD)).
		then().spec(SpecUtil.responseSpec_OK()).body("message", equalTo("Success")).
		body("data",notNullValue()).body("data",hasKey("mst_oem"))
		.body("data",hasKey("mst_model"))
		.body("data",hasKey("mst_oem")).body("$",hasKey("message")).body("data.mst_oem.size()",equalTo(2)).
		body("data.mst_oem.id",Matchers.everyItem(notNullValue())).
		body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/masterApiResponseSchema.json"));
		//In Rest Assured, this is a Groovy GPath expression
		
	}
	
	@Test
	public void invalidTokenMasterApi() throws IOException {
		given().spec(SpecUtil.requestSpec()).
		when().post("/master").
		then().spec(SpecUtil.responseSpec_TEXT(401));
	}

}
