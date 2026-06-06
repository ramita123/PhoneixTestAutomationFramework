package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class CSVReaderUtil {

	private CSVReaderUtil() {

	}

	public static <T> Iterator<T> loadCSV(String pathOfCSVFile,Class<T> bean)  {

		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(pathOfCSVFile);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

		CSVReader csvReader = new CSVReader(inputStreamReader);
		
		
	 
		CsvToBean<T> csvToBeans = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true).build();

		List<T> list = csvToBeans.parse();
		
		return list.iterator();
	}

}
