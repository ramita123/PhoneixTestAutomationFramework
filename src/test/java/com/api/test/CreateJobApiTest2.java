package com.api.test;

import static com.api.utils.DateTimeUtility.getTimeWithDaysAgo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
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
import com.api.utils.DateTimeUtility;
import com.github.javafaker.Faker;

import static com.api.utils.SpecUtil.*;

public class CreateJobApiTest2 {
	
	private CreateJobPayload createJobPayload;
	private static final String COUNTRY="India";
	
	@BeforeMethod()
	public void setUp(){

		Faker faker= new Faker(new Locale("en-IND"));
		
		
		Customer customer= new Customer(faker.name().firstName(), faker.name().lastName(), faker.numerify("83########"), faker.numerify("83########"), faker.internet().emailAddress(), faker.internet().emailAddress());
		System.out.println(customer);
		
		CustomerAdress customerAddress= new CustomerAdress(faker.numerify("###"), faker.address().streetName(), faker.address().streetName(),faker.address().streetName() , faker.address().streetName(), faker.numerify("#####"), 
				COUNTRY, faker.address().state());
		System.out.println(customerAddress);
		
		
		CustomerProduct customerProduct= new CustomerProduct(DateTimeUtility.getTimeWithDaysAgo(10), faker.numerify("###############"), faker.numerify("###############"),faker.numerify("###############"),faker.internet().url(), 1, 2);
		System.out.println(customerProduct);
		
		Random random= new Random();
		int id=random.nextInt(26)+1;
		
		System.out.println(id);
		
		Problems problems= new Problems(id, faker.lorem().sentence(5));
		System.out.println(problems);
		
		List<Problems> problemsList= new ArrayList<>();
		problemsList.add(problems);
		
		 createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
	}
	
	
	@Test(description="verifying if create job api is able to create inwarranty job",groups= {"api","regression","smoke"})
	public void createJobApiTest() throws IOException {
		
		given().spec(requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then().spec(responseSpec_OK()).
		body("message", equalTo("Job created successfully. ")).
		body(matchesJsonSchemaInClasspath("response-schema/createJobResponseSchema.json")).
		body("data.mst_service_location_id",equalTo(1)).body("data.job_number", startsWith("JOB_"))
		.body("data",hasKey("id"));
		
	//	.body(Matchers.hasProperty("data.id"));

		
	}

}
