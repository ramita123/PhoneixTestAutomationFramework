package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureEnvironmentWriterUtil {
	
	private static final Logger LOGGER= LogManager.getLogger(AllureEnvironmentWriterUtil.class);

	public static void createEnvironmentProperties() {

		String folderPath = "target/allure-results";

		File file = new File(folderPath);
		file.mkdirs();
		Properties prop = new Properties();
		prop.setProperty("Enginner Name", System.getProperty("user.name"));
		prop.setProperty("Project Name", "Phoneix Test Automation Framework");
		prop.setProperty("Environment", ConfigManager2.env);
		prop.setProperty("Base_Uri", ConfigManager2.getProperty("BASE_URI"));
		prop.setProperty("Operating version", System.getProperty("os.version"));
		prop.setProperty("Operating System", System.getProperty("os.name"));
		prop.setProperty("Java version", System.getProperty("java.version"));

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(folderPath + "/environment.properties");
			prop.store(fileWriter, "My properties file");
			LOGGER.info("Created enviornment.properties file at {}",folderPath);
			fileWriter.close();
		} catch (IOException e) {
			LOGGER.info("Unable to create the environment.properties file",e);
			e.printStackTrace();
		}
		
		
	}

}
