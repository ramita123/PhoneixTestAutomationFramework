package com.api.utils;

import static com.api.utils.ConfigManager2.getProperty;

import java.io.IOException;

import static  org.hamcrest.Matchers.*;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;

import static com.api.utils.AuthTokenProvider.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	//get-delete
	
	public static RequestSpecification requestSpec() throws IOException {
		
		
	RequestSpecification request=	new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY).build();
		
		return request;	
		
	}
	
	
	//post-put-patch
	public static RequestSpecification requestSpec(Object userCredentials)  {
		
		
		RequestSpecification requestSpecification=	new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
			.setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBody(userCredentials)
			.log(LogDetail.URI)
			.log(LogDetail.METHOD)
			.log(LogDetail.HEADERS)
			.log(LogDetail.BODY).build();
			
			return requestSpecification;	
		}
	
	
	
	
	public static RequestSpecification requestSpecWithAuth(Role role) throws IOException  {
		RequestSpecification requestSpecification=	new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).addHeader("Authorization", getToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY).build();
				
				return requestSpecification;	
		
	}
	
	
	public static RequestSpecification requestSpecWithAuth(Role role,Object payload) throws IOException  {
		RequestSpecification requestSpecification=	new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).addHeader("Authorization", getToken(role)).setBody(payload)
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY).build();
				
				return requestSpecification;	
		
	}
	
	
	public static ResponseSpecification responseSpec_OK() {
	ResponseSpecification responseSpecification=	new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200)
		.expectResponseTime(lessThan(1000l)).log(LogDetail.ALL).build();
		return responseSpecification;
	}
	
	
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification responseSpecification=	new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(statusCode)
			.expectResponseTime(lessThan(1000l)).log(LogDetail.ALL).build();
			return responseSpecification;
		}
	
	
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		ResponseSpecification responseSpecification=	new ResponseSpecBuilder().expectStatusCode(statusCode)
			.expectResponseTime(lessThan(1000l)).log(LogDetail.ALL).build();
			return responseSpecification;
		}
	
	
	

}
