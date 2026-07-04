package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;

import io.restassured.response.Response;

public class JobService {
	
	
	private static final String CREATE_ENDPOINT="/job/create";
	
	
	public Response createJob(Role role,CreateJobPayload createJobPayload) {
	Response response=	given().spec(requestSpecWithAuth(role,createJobPayload)).
		when().post(CREATE_ENDPOINT);
	return response;
	}

}
