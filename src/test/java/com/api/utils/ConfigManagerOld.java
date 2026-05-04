package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOld {

	// wap to read the peroperty file from
	// src/java/resources/config/config.properties
	
	private ConfigManagerOld() {
		
	}

	private static Properties prop = new Properties();

	// static block going to execute only once during the class loading
	static {
		File configFile = new File(
				System.getProperty("user.dir") + File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		FileReader fileReader;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) throws IOException {
		
		
		

		return prop.getProperty(key);

	}
}
