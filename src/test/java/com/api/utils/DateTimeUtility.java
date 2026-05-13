package com.api.utils;

import java.time.temporal.ChronoUnit;
import java.time.Instant;



public class DateTimeUtility {
	
	private DateTimeUtility() {
		
	}
	
	
	
	
	public static String getTimeWithDaysAgo(int days) {
		
		return Instant.now().minus(days,ChronoUnit.DAYS).toString();
		
		
	}

}
