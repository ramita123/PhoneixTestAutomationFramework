package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;

import io.restassured.response.Response;

public class JobService {
	
	
	private static final String CREATE_ENDPOINT="/job/create";
	private static final String SEARCH_ENDPOINT="/job/search";
	private static final Logger LOGGER= LogManager.getLogger(JobService.class);

	
	public Response createJob(Role role,CreateJobPayload createJobPayload) {
		LOGGER.info("mkaing request to the {} endpoint with the role {} and the payload{}",CREATE_ENDPOINT,role,createJobPayload);
	Response response=	given().spec(requestSpecWithAuth(role,createJobPayload)).
		when().post(CREATE_ENDPOINT);
	return response;
	}
	
	public Response Search(Role role,Object payload) {
		LOGGER.info("making request to the {} endpoint  with the role {} and the payload{}",SEARCH_ENDPOINT,role,payload);

		Response response=	given().spec(requestSpecWithAuth(role,payload)).
			when().post(SEARCH_ENDPOINT);
		return response;
		}

}
