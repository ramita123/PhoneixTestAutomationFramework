package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.services.DashboardService;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountApiTest {
	private DashboardService dashboardService;
	
	@BeforeMethod(description="intializing Dashboard service")
	public void setUp(){
		 dashboardService= new DashboardService();
	}

	@Test(description="verify that api is giving correct count",groups= {"api","regreession","smoke"})
	public void verifyCountApiResponse() throws IOException {

		dashboardService.count(Role.FD)
				.then().spec(responseSpec_OK())
				.body("message", equalTo("Success"))
				.body("data", notNullValue())
				.body("data.size()", equalTo(3)).and().body("data.count", everyItem(greaterThanOrEqualTo(0)))
				.and().body("data.label", everyItem(not(blankOrNullString()))).and().
				body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
				.body(matchesJsonSchemaInClasspath("response-schema/countApiResponseSchema-FD.json"));
	}

	@Test(description="verify the api is returning correct status code for invalid auth",groups= {"api","regression","smoke"})
	public void countApiTest_MissingAuthToken() throws IOException {
		dashboardService.countWithoutAuth().then().spec(responseSpec_TEXT(401));

	}

}
