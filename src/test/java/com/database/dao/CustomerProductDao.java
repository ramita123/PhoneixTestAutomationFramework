package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.CustomerProductDBModel;

public class CustomerProductDao {

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
			conn = DatabaseManager.getConnection();
			PreparedStatement prepareStatement = conn.prepareStatement(CUSTOMER_PRODUCT_QUERY);
			prepareStatement.setInt(1, customerProductId);
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				customerProductDBModel = new CustomerProductDBModel(resultSet.getInt("mst_model_id"),
						resultSet.getString("dop"), resultSet.getString("popurl"), resultSet.getString("imei2"),
						resultSet.getString("imei1"), resultSet.getString("serial_number"),
						resultSet.getInt("tr_customer_id"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerProductDBModel;

	}

}
