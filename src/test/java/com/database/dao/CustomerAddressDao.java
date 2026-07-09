package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.services.MasterService;
import com.database.DatabaseManager;
import com.database.model.CustomerAddressDBModel;

public class CustomerAddressDao {
	private static final Logger LOGGER= LogManager.getLogger(CustomerAddressDao.class);

	private final static String CUSTOMER_ADDRESS_DETAIL_QUERY = """

						SELECT  id,
			            flat_number,
			            apartment_name,
			            street_name,
			            landmark,
			            area,
			            pincode,
			            country,
			state from tr_customer_address
								where id=?
						""";
	
	private CustomerAddressDao () {};

	public static CustomerAddressDBModel getCustomerAddressInfo(int customerAddressId) {
		Connection conn = null;
		CustomerAddressDBModel customerAddressDBModel = null;
		try {
			LOGGER.info("Getting the connection from database manager");

			conn = DatabaseManager.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CUSTOMER_ADDRESS_DETAIL_QUERY);
			preparedStatement.setInt(1, customerAddressId);
			LOGGER.info("Executing the SQL query {}",CUSTOMER_ADDRESS_DETAIL_QUERY);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				customerAddressDBModel = new CustomerAddressDBModel(resultSet.getInt("id"), resultSet.getString("flat_number"),
						resultSet.getString("apartment_name"), resultSet.getString("street_name"),
						resultSet.getString("landmark"), resultSet.getString("area"), resultSet.getString("pincode"),
						resultSet.getString("country"), resultSet.getString("state"));

			}

		} catch (SQLException e) {
			LOGGER.error("cannot convert the result set to Customer Address",e);
			e.printStackTrace();
		}

		return customerAddressDBModel;
	}

}
