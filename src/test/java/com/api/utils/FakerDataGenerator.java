package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.testng.annotations.BeforeMethod;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Product;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAdress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {

	private FakerDataGenerator() {
	};

	private static final String COUNTRY = "India";
	private static Faker faker = new Faker(new Locale("en-IND"));
	private static Random random = new Random();

	private final static int[] validProblmesId = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24,
			26, 27, 28, 29 };

	private static void validProblemIds() {

	}

	public static CreateJobPayload generateFakeCreateJobData() {
		Customer customer = generateFakeCustomerData();

		CustomerAdress customerAddress = generateFakeCustomerAddressData();

		CustomerProduct customerProduct = generateFakeCustomerProductData();

		List<Problems> problemsList = generateFakeProblemsListData();

		CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),
				Platform.FRONTE_DESK.getCode(), Warranty.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer,
				customerAddress, customerProduct, problemsList);
		return createJobPayload;
	}

	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {

		List<CreateJobPayload> payloadList = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			Customer customer = generateFakeCustomerData();

			CustomerAdress customerAddress = generateFakeCustomerAddressData();

			CustomerProduct customerProduct = generateFakeCustomerProductData();

			List<Problems> problemsList = generateFakeProblemsListData();

			CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),
					Platform.FRONTE_DESK.getCode(), Warranty.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer,
					customerAddress, customerProduct, problemsList);
			payloadList.add(createJobPayload);
		}
		return payloadList.iterator();
	}

	private static Customer generateFakeCustomerData() {

		Customer customer = new Customer(faker.name().firstName(), faker.name().lastName(),
				faker.numerify("83########"), faker.numerify("83########"), faker.internet().emailAddress(),
				faker.internet().emailAddress());
		System.out.println(customer);
		return customer;
	}

	private static CustomerAdress generateFakeCustomerAddressData() {
		return new CustomerAdress(faker.numerify("###"), faker.address().streetName(), faker.address().streetName(),
				faker.address().streetName(), faker.address().streetName(), faker.numerify("#####"), COUNTRY,
				faker.address().state());

	}

	private static CustomerProduct generateFakeCustomerProductData() {
		return new CustomerProduct(DateTimeUtility.getTimeWithDaysAgo(10), faker.numerify("###############"),
				faker.numerify("###############"), faker.numerify("###############"), faker.internet().url(),
				Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());

	}

	private static List<Problems> generateFakeProblemsListData() {

		int randomIndex = random.nextInt(validProblmesId.length);

		Problems problems = new Problems(validProblmesId[randomIndex], faker.lorem().sentence(5));
		System.out.println(problems);

		List<Problems> problemsList = new ArrayList<>();
		problemsList.add(problems);
		return problemsList;
	}

}
