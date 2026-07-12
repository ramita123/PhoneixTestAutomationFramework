package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;
import com.dataproviders.api.bean.UserBean;

import static com.api.utils.SpecUtil.*;

import static com.api.utils.ConfigManager2.*;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

@Listeners(com.listeners.ApiTestListerner.class)
public class LoginApiTest {

	private UserBean userCredentials;
	private AuthService authService;

	@BeforeMethod(description = "create the payload for login api")
	public void setUp() {
		userCredentials = new UserBean("iamfd", "password");
		authService = new AuthService();
		// ConfigManager configManager= null;
	}

	@Test(description = "verify login api is working for the user FD", groups = { "api", "regression", "smoke" })
	public void loginApiTest() throws IOException {

		authService.login(userCredentials).then().spec(responseSpec_OK()).body("message", equalTo("Success"))
				.body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
	}

}
