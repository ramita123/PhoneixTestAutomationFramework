package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAdress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	
	public static void main(String[] args) {
		//create fake createjobAPI Payload
		
		Faker faker= new Faker(new Locale("en-IND"));
		
		Customer customer= new Customer(faker.name().firstName(), faker.name().lastName(), faker.numerify("83########"), faker.numerify("83########"), faker.internet().emailAddress(), faker.internet().emailAddress());
		System.out.println(customer);
		
		CustomerAdress customerAddress= new CustomerAdress(faker.numerify("###"), faker.address().streetName(), faker.address().streetName(),faker.address().streetName() , faker.address().streetName(), faker.numerify("#####"), 
				"India", faker.address().state());
		System.out.println(customerAddress);
		
		
		CustomerProduct customerProduct= new CustomerProduct(DateTimeUtility.getTimeWithDaysAgo(10), faker.numerify("###############"), faker.numerify("###############"),faker.numerify("###############"),faker.internet().url(), 1, 2);
		System.out.println(customerProduct);
		
		Random random= new Random();
		int id=random.nextInt(26)+1;
		
		System.out.println(id);
		
		Problems problems= new Problems(id, faker.lorem().sentence(10));
		System.out.println(problems);
		
		List<Problems> problemsList= new ArrayList<>();
		problemsList.add(problems);
		
		CreateJobPayload customerJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
	}

}
