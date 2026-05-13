package com.api.test;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAdress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class CreateJobApiTest {
	
	@Test
	public void createJobApiTest() throws IOException {
		
		Customer  customer= new Customer("ramita", "sambyal", "8976546789", "", "ramitasambyal@gmail.com", "");
		CustomerAdress customerAddress =new CustomerAdress("duplex", "duplex", "harsar", "duplex", "duplex", "176023", "india", "HP");
		CustomerProduct customerProduct= new CustomerProduct("2026-03-14T18:30:00.000Z", "ime_8360378289", "1364465999763484", "122405999793951", "2026-03-14T18:30:00.000Z", 1, 2);
		Problems problesm=new Problems(1, "fixing issue");
		List<Problems> problemList=new ArrayList<>();
		problemList.add(problesm);
	
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
	
		
		given().spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then().spec(SpecUtil.responseSpec_OK()).
		body("message", equalTo("Job created successfully. ")).
		body(matchesJsonSchemaInClasspath("response-schema/createJobResponseSchema.json")).
		body("data.mst_service_location_id",equalTo(1)).body("data.job_number", Matchers.startsWith("JOB_"))
		.body("data",hasKey("id"));
		
	//	.body(Matchers.hasProperty("data.id"));

		
	}

}
