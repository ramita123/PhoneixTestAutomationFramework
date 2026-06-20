package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager2;

public class DatabaseManagerOld {

	public static final String DB_URL = ConfigManager2.getProperty("DB_URL");
	public static final String DB_USERNAME = ConfigManager2.getProperty("DB_USERNAME");
	public static final String DB_PASSWORD = ConfigManager2.getProperty("DB_PASSWORD");
	public volatile static  Connection conn = null;
	
	
	private DatabaseManagerOld() {
		
	}

	public  static void createConnection() throws SQLException {
		
		if(conn==null) {
			synchronized(DatabaseManagerOld.class) {
			if(conn==null) {
		
		 conn=DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		}
		}
		}
		Statement statement=conn.createStatement();
		ResultSet resultSet=statement.executeQuery("select first_name ,last_name ,mobile_number  from tr_customer");
		while(resultSet.next()) {
			
			String firstName=	resultSet.getString("first_name");
			String lastName=	resultSet.getString("last_name");
			String mobileNumber=	resultSet.getString("mobile_number");	
			
			System.out.println(firstName+" "+lastName+" "+mobileNumber);
	}

}}
