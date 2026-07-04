package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Role;

import static com.api.utils.SpecUtil.*;

import io.restassured.response.Response;

public class MasterService {

	private static final String MASTER_DETAILS_ENDPOINT = "/master";

	public Response master(Role role) {
		Response response = given().spec(requestSpecWithAuth(role)).when().post(MASTER_DETAILS_ENDPOINT);

		return response;
	}

	public Response masterWithoutAuth() {
		Response response = given().spec(requestSpec()).when().post(MASTER_DETAILS_ENDPOINT);

		return response;
	}
}
