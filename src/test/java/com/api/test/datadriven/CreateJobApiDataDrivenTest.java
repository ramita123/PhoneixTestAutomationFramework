package com.api.test.datadriven;

import static com.api.utils.DateTimeUtility.getTimeWithDaysAgo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.api.services.JobService;

import static com.api.utils.SpecUtil.*;

public class CreateJobApiDataDrivenTest {

	private JobService jobService;

	@BeforeMethod(description = "Instantiating Job service ")
	public void setUp() {
		jobService = new JobService();
	}

	@Test(description = "verifying if create job api is able to create inwarranty job", groups = { "api", "regression",
			"smoke",
			"csv" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "CreateJobApiDataProvider")
	public void createJobApiTest(CreateJobPayload createJobPayload) throws IOException {

		jobService.createJob(Role.FD, createJobPayload).then().spec(responseSpec_OK())
				.body("message", equalTo("Job created successfully. "))
				.body(matchesJsonSchemaInClasspath("response-schema/createJobResponseSchema.json"))
				.body("data.mst_service_location_id", equalTo(1)).body("data.job_number", startsWith("JOB_"))
				.body("data", hasKey("id"));

		// .body(Matchers.hasProperty("data.id"));

	}

}
