package com.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class demo {
	private static Logger logger=LogManager.getLogger(demo.class);


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info("I want to add two numbers");
		
		int a=10;
		int b=20;
		int res=a+b;
		System.out.println(res);

	}

}
