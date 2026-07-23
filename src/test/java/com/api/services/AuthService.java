package com.api.services;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthService {
	
	//it is going to hold the api's that belongs to the auth service.

	private static final String LOGIN_ENDPOINT="/login";
	private static final Logger LOGGER= LogManager.getLogger(AuthService.class);
	
	@Step("Perform login with user credentials")
	public Response login(Object userCredentials) {
		//LOGGER.info("Making login request for the payload {}",((UserBean)userCredentials).getUsername());

	Response response=	given().spec(requestSpec(userCredentials)).
		when().post(LOGIN_ENDPOINT);
	
	return response;
		
	}
}
