package com.api.services;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;

import io.restassured.response.Response;

public class AuthService {
	
	//it is going to hold the api's that belongs to the auth service.

	private static final String LOGIN_ENDPOINT="/login";
	
	public Response login(UserCredentials userCredentials) {

	Response response=	given().spec(requestSpec(userCredentials)).
		when().post(LOGIN_ENDPOINT);
	
	return response;
		
	}
}
