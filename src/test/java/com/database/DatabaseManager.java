package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.services.MasterService;
import com.api.utils.ConfigManager2;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {

	
	public static final String DB_URL = ConfigManager2.getProperty("DB_URL");
	public static final String DB_USERNAME = ConfigManager2.getProperty("DB_USERNAME");
	public static final String DB_PASSWORD = ConfigManager2.getProperty("DB_PASSWORD");
	private static final Logger LOGGER= LogManager.getLogger(MasterService.class);

	public static HikariConfig hikariConfig;
	public volatile static HikariDataSource hikariDataSource;

	public static final int MAXIMUM_POOL_SIZE =Integer.parseInt( ConfigManager2.getProperty("MAXIMUM_POOL_SIZE"));

	public static final int MINIMUM_IDLE_COUNT = Integer.parseInt( ConfigManager2.getProperty("MINIMUM_IDLE_COUNT"));
	public static final int CONNECTION_TIMEOUT_IN_SEC =Integer.parseInt( ConfigManager2.getProperty("CONNECTION_TIMEOUT_IN_SEC"));
	public static final int IDLE_TIMEOUT_IN_SEC = Integer.parseInt( ConfigManager2.getProperty("IDLE_TIMEOUT_IN_SEC"));
	public static final String POOL_NAME = ConfigManager2.getProperty("POOL_NAME");

	public static Connection conn = null;

	private DatabaseManager() {

	}

	private static void intializePool()  {

		if (hikariDataSource == null) {
			LOGGER.warn("Database connection is not available......creating HikariDataSource");
			synchronized (DatabaseManager.class) {
				if (hikariDataSource == null) {

					hikariConfig = new HikariConfig();

					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USERNAME);
					hikariConfig.setPassword(DB_PASSWORD);

					hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE_COUNT);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SEC * 1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_IN_SEC * 1000);
					hikariConfig.setPoolName(POOL_NAME);
					LOGGER.info("Hikari datasource created");

					hikariDataSource = new HikariDataSource(hikariConfig);


				}
			}

		}

	}
	
	
	public static Connection getConnection() throws SQLException {
		Connection conn=null;
		if(hikariDataSource==null) {
			LOGGER.info("Intializing the database connection using HikariCP");
			intializePool();
		}else if(hikariDataSource.isClosed()) {
			LOGGER.error("Hikari datasource is closed");
			throw new SQLException("Hikari datasource is closed");
		}
		try {
			 conn = hikariDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}

	
}
