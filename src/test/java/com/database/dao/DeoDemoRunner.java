package com.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class DeoDemoRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<CreateJobBean>  beansList=CreateJobPayloadDataDao.getCreateJobPayloadData();
		List<CreateJobPayload> createJobPayloadList=new ArrayList<>();
		
		for(CreateJobBean bean:beansList) {
			CreateJobPayload createJobPayload=CreateJobBeanMapper.mapper(bean);
			createJobPayloadList.add(createJobPayload);
		}
		
		
		for(CreateJobPayload paylod:createJobPayloadList) {
			System.out.println(paylod);
		}

	}

}
