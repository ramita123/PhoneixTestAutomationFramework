package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.providers.api.bean.UserPojo;

public class CSVReaderUtil {

	private CSVReaderUtil() {

	}

	public static void loadCSV(String pathOfCSVFile)  {

		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(pathOfCSVFile);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

		CSVReader csvReader = new CSVReader(inputStreamReader);

		CsvToBean<UserPojo> csvToBeans = new CsvToBeanBuilder(csvReader)
				.withType(UserPojo.class)
				.withIgnoreEmptyLine(true).build();

		List<UserPojo> userList = csvToBeans.parse();
		System.out.println(userList);
	}

}
