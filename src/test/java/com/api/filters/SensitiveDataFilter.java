package com.api.filters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.services.AuthService;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {

	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		LOGGER.info("******************Request Details****************");
		LOGGER.info("Base URI {}",requestSpec.getURI());
		LOGGER.info("HTTP Method :\n {}",requestSpec.getMethod());
		redactHeaders(requestSpec);



		redactPayload(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec); // will make the requestLOGGER.info("******************Request Details****************");
		LOGGER.info("STATUS {}",response.getStatusLine());
		LOGGER.info("RESPONSE TIME ms Time :\n {}",response.timeIn(TimeUnit.MILLISECONDS));
		LOGGER.info("Response Headers :\n {}",response.getHeaders());

		redactResponse(response);

		return response;
	}

	private void redactHeaders(FilterableRequestSpecification requestSpec ) {
	List<Header> headers=	requestSpec.getHeaders().asList();		
	
	for(Header header:headers) {
		if(header.getName().equalsIgnoreCase("Authorization")) {
			LOGGER.info(header.getName(),"\"[REDACTED]\"");
		}else {
			LOGGER.info(header.getName(),header.getValue());

		}
	}
	}

	public void redactResponse(Response responseBody) {
		String response = responseBody.asPrettyString();

		response = response.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\":\"[REDACTED]\"");
		LOGGER.info("Response payload is {}", response);
	}

	public void redactPayload(FilterableRequestSpecification requestSpec) {
		
		if(requestSpec.getBody()!=null) {
		
		String requestPayload = requestSpec.getBody().toString();
		requestPayload = requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\":\"[REDACTED]\"");
		LOGGER.info("Request payload is {}", requestPayload);
		}
	}

}
