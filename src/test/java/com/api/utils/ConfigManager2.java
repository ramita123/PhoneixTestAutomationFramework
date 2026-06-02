package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager2 {

	// wap to read the peroperty file from
	// src/java/resources/config/config.properties
	private static Properties prop = new Properties();
	private static String filePath;
	private static String env;

	private ConfigManager2() {

	}

	// static block going to execute only once during the class loading
	static {

		env = System.getProperty("env","qa");
		env= env.toLowerCase().trim();

		switch (env) {

		case "dev"->

			filePath = "config" + File.separator + "config.dev.properties";
		
		case "qa"->

			filePath = "config" + File.separator + "config.qa.properties";
		

		case "uat"->

			filePath = "config"+File.separator+"config.uat.properties";
		

		default->
			filePath = "config" + File.separator + "config.properties";
			
		

		}
	
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

		if (inputStream == null) {
			throw new RuntimeException("cannot find the file at path" + filePath);
		}
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key)  {

		return prop.getProperty(key);

	}
}
