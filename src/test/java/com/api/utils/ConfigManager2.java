package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.services.MasterService;

public class ConfigManager2 {

	
	private static Properties prop = new Properties();
	private static String filePath;
	private static String env;
	private static final Logger LOGGER= LogManager.getLogger(MasterService.class);


	private ConfigManager2() {

	}

	static {
		LOGGER.info("Reading environment value passed from terminal");
		if(System.getProperty("env")==null) {
			LOGGER.warn("Environment is not set picking up the default qa environment");
		}
		
		env = System.getProperty("env","qa");
		LOGGER.info("Runnning the tests in env {}",env);

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
		
		LOGGER.info("Using the properties file from the path {}",filePath);

	
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

		if (inputStream == null) {
			LOGGER.error("cannot read the file at path",filePath);
			throw new RuntimeException("cannot find the file at path {}" + filePath);
		}
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			LOGGER.error("cannot find the file  at path {}",filePath);

			e.printStackTrace();
		}
	}

	public static String getProperty(String key)  {

		return prop.getProperty(key);

	}
}
