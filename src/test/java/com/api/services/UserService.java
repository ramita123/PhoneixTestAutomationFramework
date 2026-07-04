package com.api.services;

import static io.restassured.RestAssured.*;

import com.api.constant.Role;

import io.restassured.response.Response;

import static com.api.utils.SpecUtil.*;

public class UserService {
	
	private static final String USER_DETAILS_ENDPOINT="/userdetails";
	
	public Response userDetails(Role role) {
		Response response=given(requestSpecWithAuth(role)).when().get(USER_DETAILS_ENDPOINT);
		return response;
	}

}
