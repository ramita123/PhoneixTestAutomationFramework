package com.api.test;

import static com.api.utils.DateTimeUtility.getTimeWithDaysAgo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
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
import com.api.services.JobService;
import com.database.dao.CustomerDao;
import com.database.model.CustomerDBModel;

import static com.api.utils.SpecUtil.*;

@Listeners(com.listeners.ApiTestListerner.class)
public class CreateJobApiWithDBValidationTest {
	
	private CreateJobPayload createJobPayload;
	private Customer  customer;
	private JobService jobService;
	
	@BeforeMethod(description="create create job api payload and intializing JobService")
	public void setUp(){
		  customer= new Customer("ramita", "sambyal", "8976546789", "", "ramitasambyal@gmail.com", "");
		CustomerAdress customerAddress =new CustomerAdress("duplex", "duplex", "harsar", "duplex", "duplex", "176023", "india", "HP");
		CustomerProduct customerProduct= new CustomerProduct(getTimeWithDaysAgo(10), "ime_8360378289", "13653334468829", "121144599990951", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problesm=new Problems(Problem.OVERHEATING.getCode(),"overheatingIssue" );
		List<Problems> problemList=new ArrayList<>();
		problemList.add(problesm);
	
		createJobPayload= new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONTE_DESK.getCode(), Warranty.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);
		  jobService= new JobService();
	}
	
	
	@Test(description="verifying if create job api is able to create inwarranty job",groups= {"api","regression","smoke"})
	public void createJobApiTest() throws IOException {
		
	int customerId=	jobService.createJob(Role.FD,createJobPayload).then().spec(responseSpec_OK()).
		body("message", equalTo("Job created successfully. ")).
		body(matchesJsonSchemaInClasspath("response-schema/createJobResponseSchema.json")).
		body("data.mst_service_location_id",equalTo(1)).body("data.job_number", startsWith("JOB_"))
		.body("data",hasKey("id"))
		.extract().body().jsonPath().getInt("data.tr_customer_id");
	
	System.out.println("**************************"+customerId);
	CustomerDBModel customerDataFromDB=	CustomerDao.getCustomerInfo(customerId);
	Assert.assertEquals(customerDataFromDB.getFirst_name(), customer.first_name());
	Assert.assertEquals(customerDataFromDB.getLast_name(), customer.last_name());
	Assert.assertEquals(customerDataFromDB.getEmail_id(), customer.email_id());
	
		
	//	.body(Matchers.hasProperty("data.id"));

		
	}

}
