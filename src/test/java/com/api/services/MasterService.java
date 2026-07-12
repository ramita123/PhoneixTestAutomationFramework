package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;

import static com.api.utils.SpecUtil.*;

import io.restassured.response.Response;

public class MasterService {

	private static final String MASTER_DETAILS_ENDPOINT = "/master";
	private static final Logger LOGGER= LogManager.getLogger(MasterService.class);

	public Response master(Role role) {
		LOGGER.info("making api for end point {} with role {}",MASTER_DETAILS_ENDPOINT,role);
		Response response = given().spec(requestSpecWithAuth(role)).when().post(MASTER_DETAILS_ENDPOINT);

		return response;
	}

	public Response masterWithoutAuth() {
		LOGGER.info("making api for end point {} without the auth token",MASTER_DETAILS_ENDPOINT);

		Response response = given().spec(requestSpec()).when().post(MASTER_DETAILS_ENDPOINT);

		return response;
	}
}
