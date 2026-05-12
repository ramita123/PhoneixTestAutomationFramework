package com.api.test;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAdress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreateJobApiTest {
	
	@Test
	public void createJobApiTest() throws IOException {
		
		Customer  customer= new Customer("ramita", "sambyal", "8976546789", "", "ramitasambyal@gmail.com", "");
		CustomerAdress customerAddress =new CustomerAdress("duplex", "duplex", "harsar", "duplex", "duplex", "176023", "india", "HP");
		CustomerProduct customerProduct= new CustomerProduct("2026-03-14T18:30:00.000Z", "ime_8360378289", "131305999703484", "131305999793951", "2026-03-14T18:30:00.000Z", 1, 2);
		Problems problesm=new Problems(1, "fixing issue");
		Problems problemsArray[]=new Problems[1];
		problemsArray[0]=problesm;
		
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
	
		
		given().spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then().spec(SpecUtil.responseSpec_OK()).body("message", equalTo("Job created successfully. "));
	//	.body(Matchers.hasProperty("data.id"));

		
	}

}
