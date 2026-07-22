package com.api.services;

import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.api.utils.SpecUtil.*;

public class UserService {
	
	private static final String USER_DETAILS_ENDPOINT="/userdetails";
	private static final Logger LOGGER= LogManager.getLogger(MasterService.class);

	@Step("making search api request for user details")
	public Response userDetails(Role role) {
		LOGGER.info("making api for end point {} with role {}",USER_DETAILS_ENDPOINT,role);

		Response response=given(requestSpecWithAuth(role)).when().get(USER_DETAILS_ENDPOINT);
		return response;
	}

}
