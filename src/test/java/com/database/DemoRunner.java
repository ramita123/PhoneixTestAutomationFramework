package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoRunner {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn=DatabaseManager.getConnection();
		System.out.println(conn);
		//Statement statement = conn.createStatement();
		//ResultSet resultSet = statement
				//.executeQuery("select first_name ,last_name ,mobile_number  from tr_customer");

	}

}
