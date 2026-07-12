package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.services.MasterService;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterApiTest {
	
	MasterService masterService;
	@BeforeMethod(description="Intialize the MAsterService")
	public void setUp(){
		 masterService= new MasterService();	
	}
	
	@Test(description="verify master api giving correct response",groups= {"api","regression","smoke"})
	public void masterApiTest() throws IOException {
		
		masterService.master(Role.FD).
		then().spec(responseSpec_OK()).body("message", equalTo("Success")).
		body("data",notNullValue()).body("data",hasKey("mst_oem"))
		.body("data",hasKey("mst_model"))
		.body("data",hasKey("mst_oem")).body("$",hasKey("message")).body("data.mst_oem.size()",equalTo(2)).
		body("data.mst_oem.id",everyItem(notNullValue())).
		body(matchesJsonSchemaInClasspath("response-schema/masterApiResponseSchema.json"));
		//In Rest Assured, this is a Groovy GPath expression
		
	}
	
	@Test(description="verify the api is returning correct status code for invalid authorization",groups= {"api","regression","smoke"})
	public void invalidTokenMasterApi() throws IOException {
		masterService.masterWithoutAuth().
		then().spec(responseSpec_TEXT(401));
	}

}
