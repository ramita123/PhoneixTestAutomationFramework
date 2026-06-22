package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.database.dao.CreateJobPayloadDataDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	
	@DataProvider(name="LoginApiDataProvider",parallel=true)
	public static Iterator<UserBean> loginApiDataProvider() {
		
		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv",UserBean.class);
		
	}
	
	
	
	@DataProvider(name="CreateJobApiDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> createJobApiDataProvider() {
		
	Iterator<CreateJobBean> createJobBeanList= CSVReaderUtil.loadCSV("testData/CreateJobData.csv",CreateJobBean.class);
	List<CreateJobPayload> payloadList= new ArrayList<>();
	while(createJobBeanList.hasNext()) {
		CreateJobBean tempBean=createJobBeanList.next();
		
		CreateJobPayload tempPayload=CreateJobBeanMapper.mapper(tempBean);
		payloadList.add(tempPayload);
	}
	
	return payloadList.iterator();
		
	}
	
	
	@DataProvider(name="CreateJobApiFakeDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> createJobApiFakeDataProvider() {
		String fakerCount=System.getProperty("fakerCount", "5");
	int fakerCountInt=	Integer.parseInt(fakerCount);
		
		
	return	FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
	}
	
	@DataProvider(name="createJobApiDBDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> createJobApiDBDataProvider() {
		List<CreateJobBean>  beansList=CreateJobPayloadDataDao.getCreateJobPayloadData();
		List<CreateJobPayload> createJobPayloadList=new ArrayList<>();
		
		for(CreateJobBean bean:beansList) {
			CreateJobPayload createJobPayload=CreateJobBeanMapper.mapper(bean);
			createJobPayloadList.add(createJobPayload);
		}
		
		
		return createJobPayloadList.iterator();
	}

}
