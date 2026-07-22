package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.services.MasterService;
import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import io.qameta.allure.Step;

public class CSVReaderUtil {
	private static final Logger LOGGER= LogManager.getLogger(MasterService.class);


	private CSVReaderUtil() {

	}

	@Step("Loading test data from CSV file")
	public static <T> Iterator<T> loadCSV(String pathOfCSVFile,Class<T> bean)  {
		LOGGER.info("Loding the CSV file {}",pathOfCSVFile);
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(pathOfCSVFile);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

		CSVReader csvReader = new CSVReader(inputStreamReader);
		
		
		LOGGER.info("Converting the csv to bean class",bean);
		CsvToBean<T> csvToBeans = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true).build();
		
		

		List<T> list = csvToBeans.parse();
		
		
		return list.iterator();
	}

}
