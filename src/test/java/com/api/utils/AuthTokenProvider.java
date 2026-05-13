package com.api.utils;

import static com.api.utils.ConfigManager2.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.hamcrest.Matchers;

import static com.api.constant.Role.*;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class AuthTokenProvider {



	private AuthTokenProvider() {
		// private constructor;
	}

	public static String getToken(Role role) throws IOException {
		// TODO Auto-generated method stub
		UserCredentials userCredentials = null;
		if (role==FD) {

			 userCredentials = new UserCredentials("iamfd", "password");
			// ConfigManager configManager= null;

		} else if (role==SUP) {
			 userCredentials = new UserCredentials("iamsup", "password");
			// ConfigManager configManager= null;

		} else if(role==QC) {
			 userCredentials = new UserCredentials("iamqc", "password");
			// ConfigManager configManager= null;

		}
		else if(role==FD) {
			 userCredentials = new UserCredentials("iameng", "password");
			// ConfigManager configManager= null;

		}
		String token = given().baseUri(getProperty("BASE_URI")).and().contentType(ContentType.JSON).and()
				.accept(ContentType.JSON).and().body(userCredentials).log().headers().and().log().body().when()
				.post("/login").then().log().ifValidationFails().body("message", Matchers.equalTo("Success")).extract()
				.body().jsonPath()

				.getString("data.token");
		return token;
	}

}
