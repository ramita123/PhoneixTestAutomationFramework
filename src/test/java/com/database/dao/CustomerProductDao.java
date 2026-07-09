package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.services.MasterService;
import com.database.DatabaseManager;
import com.database.model.CustomerProductDBModel;

public class CustomerProductDao {
	private static final Logger LOGGER= LogManager.getLogger(CustomerProductDao.class);


	private static final String CUSTOMER_PRODUCT_QUERY = """
			SELECT  tr_customer_id,
			          mst_model_id,
			          dop,
			           	popurl,
			         imei2,
			          imei1,
			        serial_number from tr_customer_product  where id =?
			""";

	private CustomerProductDao() {

	}

	public static CustomerProductDBModel getCustomerProduct(int customerProductId) {
		Connection conn = null;
		CustomerProductDBModel customerProductDBModel = null;
		try {
			LOGGER.info("Getting the connection from database manager");

			conn = DatabaseManager.getConnection();
			PreparedStatement prepareStatement = conn.prepareStatement(CUSTOMER_PRODUCT_QUERY);
			prepareStatement.setInt(1, customerProductId);
			LOGGER.info("Executing the SQL query {}",CUSTOMER_PRODUCT_QUERY);

			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				customerProductDBModel = new CustomerProductDBModel(resultSet.getInt("mst_model_id"),
						resultSet.getString("dop"), resultSet.getString("popurl"), resultSet.getString("imei2"),
						resultSet.getString("imei1"), resultSet.getString("serial_number"),
						resultSet.getInt("tr_customer_id"));

			}

		} catch (SQLException e) {
			LOGGER.error("cannot convert the result set to Customer product ",e);
			e.printStackTrace();
		}
		return customerProductDBModel;

	}

}
