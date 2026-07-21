package com.api.test;

import static  io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.services.UserService;

import static com.api.utils.SpecUtil.*;

import static com.api.utils.AuthTokenProvider.*;

import static  com.api.utils.ConfigManager2.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

@Epic("User Management")
@Feature("USer Details")
@Listeners(com.listeners.ApiTestListerner.class)

public class UserDetailsApiTest {
	
	private UserService userService;
	
	@BeforeMethod(description="intializing the userservice ")
	public void setUp() {
		 userService= new UserService();
	}
	
	
	@Story("User details should be shown")
	@Description("verify user details response shown correctly and it belongs to the group ")
	@Severity(SeverityLevel.CRITICAL)
	
	@Test(description="verify user details response shown correctly and it belongs to the group ",groups= {"api","regression","smoke"})
	public void userDetailsAPITest() throws IOException {
		
		userService.userDetails(FD).then().spec(responseSpec_OK())
		.body("message",equalTo("Success")).body(matchesJsonSchemaInClasspath("response-schema/userDetailsSchema.json"));
	}

}
