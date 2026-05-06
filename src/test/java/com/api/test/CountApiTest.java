package com.api.test;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.utils.ConfigManager2.*;

import static io.restassured.RestAssured.*;

public class CountApiTest {

	@Test
	public void verifyCountApiResponse() throws IOException {

		given().baseUri(getProperty("BASE_URI")).contentType(ContentType.JSON).and().accept(ContentType.JSON)
				.header("Authorization", getToken(Role.FD)).log().uri().and().log().method().and().log().headers()
				.when().get("/dashboard/count").then().log().ifValidationFails().statusCode(200).and()
				.time(lessThan(1000l)).and().body("message", Matchers.equalTo("Success")).body("data", notNullValue())
				.body("data.size()", equalTo(3)).and().body("data.count", Matchers.everyItem(greaterThanOrEqualTo(0)))
				.and().body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString()))).and().
				body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/countApiResponseSchema-FD.json"));
	}

	@Test
	public void countApiTest_MissingAuthToken() throws IOException {
		given().baseUri(getProperty("BASE_URI")).contentType(ContentType.JSON).and().accept(ContentType.JSON)
		.log().uri().and().log().method().and().log().headers().
		when().get("/dashboard/count").then().log().ifValidationFails().statusCode(401);

	}

}
