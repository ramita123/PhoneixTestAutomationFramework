package com.api.utils;

import static com.api.utils.ConfigManager2.getProperty;

import java.io.IOException;

import static org.hamcrest.Matchers.*;

import com.api.constant.Role;
import com.api.filters.SensitiveDataFilter;
import com.api.request.model.UserCredentials;

import static com.api.utils.AuthTokenProvider.*;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	// get-delete

	@Step("Setting up the base URI, content type and attaching the sensitive data filter")
	public static RequestSpecification requestSpec() {

		RequestSpecification request = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).build();

		return request;

	}

	@Step("Setting up the base URI, content type and attaching the sensitive data filter")
	// post-put-patch
	public static RequestSpecification requestSpec(Object userCredentials) {

		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBody(userCredentials)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				.build();

		return requestSpecification;
	}

	@Step("Setting up the base URI, content type and attaching the sensitive data filter for a role" )
	public static RequestSpecification requestSpecWithAuth(Role role) {
		RequestSpecification requestSpecification = null;
		try {
			requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
					.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
					.addHeader("Authorization", getToken(role)).
					addFilter(new SensitiveDataFilter())
					.addFilter(new AllureRestAssured())
					.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return requestSpecification;

	}

	@Step("Setting up the base URI, content type and attaching the sensitive data filter")
	public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {
		RequestSpecification requestSpecification = null;
		try {
			requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
					.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
					.addHeader("Authorization", getToken(role)).setBody(payload).
					addFilter(new SensitiveDataFilter())
					.addFilter(new AllureRestAssured()).
					build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return requestSpecification;

	}

	@Step("Setting up the expected content type,expected response time and expected status code")
	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectResponseTime(lessThan(1000l)).build();
		return responseSpecification;
	}

	@Step("Expecting  the expected content type,expected response time and expected status code")
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(statusCode).expectResponseTime(lessThan(1000l)).build();
		return responseSpecification;
	}

	@Step("Expecting the expected content type,expected response time and expected status code")
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(statusCode)
				.expectResponseTime(lessThan(1000l)).build();
		return responseSpecification;
	}

}
