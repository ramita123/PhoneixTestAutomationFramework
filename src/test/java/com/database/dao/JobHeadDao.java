package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.services.MasterService;
import com.database.DatabaseManager;
import com.database.model.JobHeadDBModel;
import com.database.model.MapJobProblemDBModel;

public class JobHeadDao {
	
	private static final Logger LOGGER= LogManager.getLogger(JobHeadDao.class);

	
	private static final String JOB_HEAD_QUERY="""
			SELECT * from tr_job_head tjh where id=?

			""";
	
	
	private JobHeadDao() {
		
	}
	
	public static JobHeadDBModel getJobHeadId(int id) {
		Connection conn= null;
		JobHeadDBModel jobHeadDBModel = null;
		
		try {
			LOGGER.info("Getting the connection from database manager");

			 conn=DatabaseManager.getConnection();
			PreparedStatement preparedStatement= conn.prepareStatement(JOB_HEAD_QUERY);
			preparedStatement.setInt(1, id);
			LOGGER.info("Executing the SQL query {}",JOB_HEAD_QUERY);

		ResultSet resSet=	preparedStatement.executeQuery();
		
		while(resSet.next()) {
			
			 jobHeadDBModel = new JobHeadDBModel(resSet.getInt("mst_warrenty_status_id"),resSet.getInt("mst_service_location_id"),
					 resSet.getInt("mst_platform_id"),resSet.getInt("mst_oem_id"));
			
			
		}
		} catch (SQLException e) {
			LOGGER.error("cannot convert the result set to JOB Head ",e);
			e.printStackTrace();
		}
		return jobHeadDBModel;
		
	}

}
