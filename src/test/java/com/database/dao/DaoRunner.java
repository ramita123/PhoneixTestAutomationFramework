package com.database.dao;

import java.sql.SQLException;

import com.api.request.model.Customer;
import com.database.model.CustomerDBModel;

public class DaoRunner {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		CustomerDBModel customerDBData=CustomerDao.getCustomerInfo();;
		System.out.println(customerDBData.getFirst_name());
		
		Customer  customer= new Customer("ramita", "sambyal", "8976546789", "", "ramitasambyal@gmail.com", "");
		
		System.out.println(customer.first_name());


	}

}
