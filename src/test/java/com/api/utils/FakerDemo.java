package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Faker faker= new Faker(new Locale("en-FRANCE"));
	String firstName=	faker.name().firstName();
	String lastName=	faker.name().lastName();
	
String buildingNumber=	faker.address().buildingNumber();
//String streetAddress=	faker.address().streetAddress();
	
	System.out.println(firstName);
	System.out.println(lastName);
	System.out.println(buildingNumber);
	System.out.println(faker.address().streetAddress());
	System.out.println(faker.numerify("704#########"));
	System.out.println(faker.internet().emailAddress());
	System.out.println(faker.phoneNumber().cellPhone());

	}

}
