package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager2;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikaricpDemo {
	public static final String DB_URL = ConfigManager2.getProperty("DB_URL");
	public static final String DB_USERNAME = ConfigManager2.getProperty("DB_USERNAME");
	public static final String DB_PASSWORD = ConfigManager2.getProperty("DB_PASSWORD");

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		
	HikariConfig hikariConfig=	new HikariConfig();
	
	hikariConfig.setJdbcUrl(DB_URL);
	hikariConfig.setUsername(DB_USERNAME);
	hikariConfig.setPassword(DB_PASSWORD);
	hikariConfig.setMinimumIdle(0);
	hikariConfig.setConnectionTimeout(10000);
	hikariConfig.setIdleTimeout(0);
	hikariConfig.setPoolName("Phonexi");
	
	HikariDataSource hikariDataSource=	new HikariDataSource(hikariConfig);
	
	Connection conn=hikariDataSource.getConnection();
	System.out.println(conn);
	Statement statement=conn.createStatement();
	ResultSet resultSet=statement.executeQuery("select first_name ,last_name ,mobile_number  from tr_customer");
	
	
	while(resultSet.next()) {
		String firstName=	resultSet.getString("first_name");
		String lastName=	resultSet.getString("last_name");
		String mobileNumber=	resultSet.getString("mobile_number");	
		
		System.out.println(firstName+" "+lastName+" "+mobileNumber);
	}
	
	hikariDataSource.close();
	
	
	
	

	}

}
