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
import com.api.response.model.CreateJobResponseModel;
import com.api.services.JobService;
import com.api.utils.DateTimeUtility;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.dao.JobHeadDao;
import com.database.dao.MapJobProblemDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.JobHeadDBModel;
import com.database.model.MapJobProblemDBModel;

import static com.api.utils.FakerDataGenerator.*;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static com.api.utils.SpecUtil.*;

@Listeners(com.listeners.ApiTestListerner.class)
public class CreateJobApiTestWithFakeData {
	private CreateJobPayload createJobPayload;
	private Customer customer;
	private CustomerAdress customerAddress;
	private JobService jobService;
	@BeforeMethod
	public void setUp() {
		createJobPayload = generateFakeCreateJobData();
		customer = createJobPayload.customer();
		customerAddress = createJobPayload.customer_address();
		
		jobService= new JobService();
		
	}

	@Test(description="verifying if create job api is able to create inwarranty job",groups= {"api","regression","smoke"})
	public void createJobApiTest() throws IOException {
		
		CreateJobResponseModel createJobResponseModel=jobService.createJob(Role.FD,createJobPayload).then().spec(responseSpec_OK()).
		body("message", equalTo("Job created successfully. ")).
		body(matchesJsonSchemaInClasspath("response-schema/createJobResponseSchema.json")).
		body("data.mst_service_location_id",equalTo(1)).body("data.job_number", startsWith("JOB_"))
		.body("data",hasKey("id"))
		.extract().response().as(CreateJobResponseModel.class);
	
	int customerId=createJobResponseModel.getData().getTr_customer_id();
	int customerProductId=createJobResponseModel.getData().getTr_customer_product_id();
	
	
	System.out.println("**************************"+customerId);
	System.out.println("**************************"+customerProductId);
	CustomerDBModel customerDBModel=	CustomerDao.getCustomerInfo(customerId);
	Assert.assertEquals(customerDBModel.getFirst_name(), customer.first_name());
	Assert.assertEquals(customerDBModel.getLast_name(), customer.last_name());
	Assert.assertEquals(customerDBModel.getEmail_id(), customer.email_id());
	
    int customerAddressId=	customerDBModel.getTr_customer_address_id();
    System.out.println("customerAddressId**************************"+customerAddressId);
	CustomerAddressDBModel customerAddressFromDataBase=CustomerAddressDao.getCustomerAddressInfo(customerAddressId);
	
	Assert.assertEquals(customerAddressFromDataBase.getFlat_number(),customerAddress.flat_number());
	Assert.assertEquals(customerAddressFromDataBase.getApartment_name(),customerAddress.apartment_name());
	Assert.assertEquals(customerAddressFromDataBase.getStreet_name(),customerAddress.street_name());
	Assert.assertEquals(customerAddressFromDataBase.getLandmark(),customerAddress.landmark());
	Assert.assertEquals(customerAddressFromDataBase.getArea(),customerAddress.area());
	Assert.assertEquals(customerAddressFromDataBase.getPincode(),customerAddress.pincode());
	Assert.assertEquals(customerAddressFromDataBase.getCountry(),customerAddress.country());
	Assert.assertEquals(customerAddressFromDataBase.getState(),customerAddress.state());
	
	
	
	
	
	int tr_problem_job_id=createJobResponseModel.getData().getId();
	
	MapJobProblemDBModel jobDataFromDB= MapJobProblemDao.getProblemDetails(tr_problem_job_id);
	List<Problems> problems= createJobPayload.problems();
	Assert.assertEquals(jobDataFromDB.getMst_problem_id(), problems.get(0).id());
	Assert.assertEquals(jobDataFromDB.getRemark(), problems.get(0).remark());
	
	
	CustomerProductDBModel customerProductDataFromDb=CustomerProductDao.getCustomerProduct(customerProductId);
	CustomerProduct customerProduct = createJobPayload.customer_product();
	Assert.assertEquals(customerProductDataFromDb.getMst_model_id(), customerProduct.mst_model_id());
	Assert.assertEquals(customerProductDataFromDb.getPopurl(), customerProduct.popurl());
	
	
JobHeadDBModel jobHeadDataFromDB=	JobHeadDao.getJobHeadId(createJobResponseModel.getData().getId());

Assert.assertEquals(jobHeadDataFromDB.getMst_service_location_id(), createJobPayload.mst_service_location_id());
Assert.assertEquals(jobHeadDataFromDB.getMst_platform_id(), createJobPayload.mst_platform_id());

Assert.assertEquals(jobHeadDataFromDB.getMst_warrenty_status_id(), createJobPayload.mst_warrenty_status_id());

Assert.assertEquals(jobHeadDataFromDB.getMst_oem_id(), createJobPayload.mst_oem_id());

	}

}
