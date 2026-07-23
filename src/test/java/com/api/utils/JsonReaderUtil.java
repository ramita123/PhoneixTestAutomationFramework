package com.api.utils;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import tools.jackson.databind.ObjectMapper;

public class JsonReaderUtil {
	
	
	public static <T> Iterator<T> loadJson(String filePath,Class<T[]> clazz) {
		InputStream path= Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

		
		ObjectMapper objectMapper= new ObjectMapper();
		
	T[] classArray= 	objectMapper.readValue(path, clazz);
	List<T> list=Arrays.asList(classArray);
	
	return list.iterator();
		
		
		
	}

}
