package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.SpecUtil.*;

import static com.api.utils.ConfigManager2.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {

	@Test
	public void loginApiTest() throws IOException {

		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		// ConfigManager configManager= null;

		given().spec(requestSpec(userCredentials)).
		when().post("/login").
		then().spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
	}

}
