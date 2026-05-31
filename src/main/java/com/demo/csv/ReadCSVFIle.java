package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFIle {

	public static void main(String[] args) throws IOException, CsvException {
		// code to read the csv file in java
		//FileReader reader= new FileReader(inputStream));

		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/LoginCreds.csv");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

		CSVReader csvReader = new CSVReader(inputStreamReader);
		List<String[]> listArray = csvReader.readAll();

		for (String[] data : listArray) {
			for (String strData : data) {
				System.out.print(strData + " ");
			}
			System.out.println();
		}
	}

}
