package com.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.database.DatabaseManager;
import com.dataproviders.api.bean.CreateJobBean;

public class CreateJobPayloadDataDao {

	private static final String SQL_QUERY = """
						SELECT c.first_name,
			       c.last_name,
			       c.mobile_number,
			       c.mobile_number_alt,
			       c.email_id,
			       c.email_id_alt,
			         a.flat_number,
			   a.apartment_name,
			    a.street_name,
			    a.landmark,
			    a.area,
			    a.pincode,
			    a.country,
			    a.state,
			    p.mst_model_id,
			p.dop,
			p.popurl,
			p.imei2,
			p.imei1,
			p.serial_number,
			mp.remark,
			mp.mst_problem_id,
			h.mst_service_location_id,
			h.mst_warrenty_status_id,
			h.mst_oem_id,
			h.mst_platform_id
			   FROM tr_customer as c
			INNER JOIN tr_customer_address as a
			    ON c.tr_customer_address_id = a.id
			INNER JOIN tr_customer_product as p
			    ON p.tr_customer_id = c.id
			INNER JOIN tr_job_head as h
			    ON h.tr_customer_id = c.id
			INNER JOIN map_job_problem as mp
			    ON mp.tr_job_head_id = h.id

			LIMIT 5;
						""";

	public static List<CreateJobBean> getCreateJobPayloadData() {
		Connection conn;
		Statement statement;
		ResultSet resultSet;
		
		List<CreateJobBean> beanList= new ArrayList<>();
		try {
			conn = DatabaseManager.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(SQL_QUERY);

			while (resultSet.next()) {
				CreateJobBean bean = new CreateJobBean();
				bean.setCustomerFirstName(resultSet.getString("first_name"));
				bean.setCustomerLastName(resultSet.getString("last_name"));
				bean.setCustomerMobileNumber(resultSet.getString("mobile_number"));
				bean.setCustomerMobileNumberAlt(resultSet.getString("mobile_number_alt"));
				bean.setCustomerEmailId(resultSet.getString("email_id"));
				bean.setCustomerEmailIdAlt(resultSet.getString("email_id_alt"));
				bean.setCustomerAddressFlatNumber(resultSet.getString("flat_number"));
				bean.setCustomerAddressApartmentName(resultSet.getString("apartment_name"));
				bean.setCustomerAddressStreetName(resultSet.getString("street_name"));
				bean.setCustomerAddressLandmark(resultSet.getString("landmark"));
				bean.setCustomerAddressArea(resultSet.getString("area"));
				bean.setCustomerAddressPincode(resultSet.getString("pincode"));
				bean.setCustomerAddressState(resultSet.getString("state"));
				bean.setCustomerAddressCountry(resultSet.getString("country"));
				bean.setCustomerProductMstModelId("1");
				bean.setCustomerProductDop(resultSet.getString("dop"));
				bean.setCustomerProductPopurl(resultSet.getString("popurl"));
				bean.setCustomerProductImei1(resultSet.getString("imei1"));
				bean.setCustomerProductImei2(resultSet.getString("imei2"));
				bean.setCustomerProductSerialNumber(resultSet.getString("serial_number"));
				bean.setProblemsRemark(resultSet.getString("mp.remark"));
				bean.setProblemsId(resultSet.getString("mst_problem_id"));
				bean.setMstServiceLocationId(resultSet.getString("mst_service_location_id"));
				bean.setMstWarrentyStatusId(resultSet.getString("mst_warrenty_status_id"));
				bean.setMstOemId("1");
				bean.setMstPlatformId(resultSet.getString("mst_platform_id"));
				bean.setCustomerProductProductId("1");
			
				beanList.add(bean);
		

			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}for(CreateJobBean beans:beanList) {
			System.out.println(beans);
		}
		
		return beanList;

		
		
	}

}
