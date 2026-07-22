package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.api.services.MasterService;
import com.github.javafaker.Faker;

import io.qameta.allure.Step;

public class FakerDataGenerator {

	private FakerDataGenerator() {
	};

	private static final String COUNTRY = "India";
	private static Faker faker = new Faker(new Locale("en-IND"));
	private static Random random = new Random();
	private static final Logger LOGGER= LogManager.getLogger(MasterService.class);

	private final static int[] validProblmesId = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24,
			26, 27, 28, 29 };

	private static void validProblemIds() {

	}
	
	@Step("Generating fake create job data")
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

	@Step("Generating fake create job data with the count")

	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		LOGGER.info("generating the fake {}  payload for create job",count);

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

	@Step("Generating fake customer data")
	private static Customer generateFakeCustomerData() {

		Customer customer = new Customer(faker.name().firstName(), faker.name().lastName(),
				faker.numerify("83########"), faker.numerify("83########"), faker.internet().emailAddress(),
				faker.internet().emailAddress());
		System.out.println(customer);
		return customer;
	}
	@Step("Generating fake customer address")
 static CustomerAdress generateFakeCustomerAddressData() {
		return new CustomerAdress(faker.numerify("###"), faker.address().streetName(), faker.address().streetName(),
				faker.address().streetName(), faker.address().streetName(), faker.numerify("#####"), COUNTRY,
				faker.address().state());

	}
	
	@Step("Generating fake customer product")

	private static CustomerProduct generateFakeCustomerProductData() {
		return new CustomerProduct(DateTimeUtility.getTimeWithDaysAgo(10), faker.numerify("###############"),
				faker.numerify("###############"), faker.numerify("###############"), faker.internet().url(),
				Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());

	}

	@Step("Generating fake problem list data")
	private static List<Problems> generateFakeProblemsListData() {
		List<Problems> problemsList = new ArrayList<>();
		int randomIndex;
		int count = random.nextInt(3) + 1;

		for (int i = 1; i <=count; i++) {

			randomIndex = random.nextInt(validProblmesId.length);

			Problems problems = new Problems(validProblmesId[randomIndex], faker.lorem().sentence(5));

			problemsList.add(problems);
		}
		System.out.println(problemsList);

		return problemsList;
	}

}
