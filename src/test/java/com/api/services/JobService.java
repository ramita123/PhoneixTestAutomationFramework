package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;

import io.restassured.response.Response;

public class JobService {
	
	
	private static final String CREATE_ENDPOINT="/job/create";
	private static final String SEARCH_ENDPOINT="/job/search";
	
	
	public Response createJob(Role role,CreateJobPayload createJobPayload) {
	Response response=	given().spec(requestSpecWithAuth(role,createJobPayload)).
		when().post(CREATE_ENDPOINT);
	return response;
	}
	
	public Response Search(Role role,Object payload) {
		Response response=	given().spec(requestSpecWithAuth(role,payload)).
			when().post(SEARCH_ENDPOINT);
		return response;
		}

}
