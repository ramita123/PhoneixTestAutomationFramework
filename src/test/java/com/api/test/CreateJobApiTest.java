package com.api.test;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAdress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager2;
import static com.api.utils.DateTimeUtility.*;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class CreateJobApiTest {
	
	@Test
	public void createJobApiTest() throws IOException {
		
		Customer  customer= new Customer("ramita", "sambyal", "8976546789", "", "ramitasambyal@gmail.com", "");
		CustomerAdress customerAddress =new CustomerAdress("duplex", "duplex", "harsar", "duplex", "duplex", "176023", "india", "HP");
		CustomerProduct customerProduct= new CustomerProduct(getTimeWithDaysAgo(10), "ime_8360378289", "1364565999783484", "122403999793951", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problesm=new Problems(Problem.OVERHEATING.getCode(),"overheatingIssue" );
		List<Problems> problemList=new ArrayList<>();
		problemList.add(problesm);
	
		CreateJobPayload createJobPayload= new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONTE_DESK.getCode(), Warranty.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);
	
		
		given().spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then().spec(SpecUtil.responseSpec_OK()).
		body("message", equalTo("Job created successfully. ")).
		body(matchesJsonSchemaInClasspath("response-schema/createJobResponseSchema.json")).
		body("data.mst_service_location_id",equalTo(1)).body("data.job_number", Matchers.startsWith("JOB_"))
		.body("data",hasKey("id"));
		
	//	.body(Matchers.hasProperty("data.id"));

		
	}

}
