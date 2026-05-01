package com.api.test;

import static  io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static  org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;



public class UserDetailsAPITest {
	
	
	@Test
	public void userDetailsAPITest() {
		
		Header header= new Header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3Nzc2MzU4NjR9.X-v1CBEnZIYiYJywlrVlJsqIDhkS0ZzyemBKiWNX8d4");
		given().baseUri("http://64.227.160.186:9000/v1").contentType(ContentType.JSON).and().accept(ContentType.JSON).and().header(header).
		when().get("userdetails").then().log().all().statusCode(200).and().time(lessThan(1500l)).
		and().body("message",equalTo("Success")).body(matchesJsonSchemaInClasspath("response-schema/userDetailsSchema.json"));
	}

}
