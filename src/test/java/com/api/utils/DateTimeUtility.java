package com.api.utils;

import java.time.temporal.ChronoUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.services.MasterService;

import java.time.Instant;



public class DateTimeUtility {

	
	private DateTimeUtility() {
		
	}
	
	
	
	
	public static String getTimeWithDaysAgo(int days) {
		
		return Instant.now().minus(days,ChronoUnit.DAYS).toString();
		
		
	}

}
