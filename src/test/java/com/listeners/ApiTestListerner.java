package com.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.utils.AllureEnvironmentWriterUtil;

public class ApiTestListerner implements ITestListener {
	
	private static final Logger LOGGER= LogManager.getLogger(ApiTestListerner.class);

	public void onTestStart(ITestResult result) {
	    // not implemented
		LOGGER.info("starting the test {}",result.getName());
		LOGGER.info("==========test class {}========",result.getMethod().getTestClass());
		LOGGER.info("Description {}",result.getMethod().getDescription());
		LOGGER.info("groups {}",Arrays.toString(result.getMethod().getGroups()));


	  }
	
	public void onTestSuccess(ITestResult result) {
		long time=result.getEndMillis()-result.getStartMillis();
		LOGGER.info("Total duration {}",time);
		LOGGER.info("{}-----Test passes 1",result.getName());
	  }
	
	
	public  void onTestFailure(ITestResult result) {
		LOGGER.error("{}-- Test Failed !",result.getName());
		LOGGER.error("Error message",result.getThrowable());
	  }
	
	 public void onTestSkipped(ITestResult result) {
			LOGGER.info("Test Skipped",result.getName());
		  }

	 public  void onStart(ITestContext context) {
		 LOGGER.info("Test start and want to check if allure creating .properties file");
		 AllureEnvironmentWriterUtil.createEnvironmentProperties();
		   
		  }
	 
	 public void onFinish(ITestContext context) {
		    // not implemented
		  }
}
