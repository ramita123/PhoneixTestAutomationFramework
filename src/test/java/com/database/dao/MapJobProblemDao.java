package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.services.MasterService;
import com.database.DatabaseManager;
import com.database.model.MapJobProblemDBModel;

public class MapJobProblemDao {
	
	private static final Logger LOGGER= LogManager.getLogger(MapJobProblemDao.class);

	private static final String JOB_PROBLEM_QUERY="""
			SELECT * from map_job_problem mjp where mjp.tr_job_head_id =?;
			""";
	
	private MapJobProblemDao() {};
	
	
	public static MapJobProblemDBModel getProblemDetails(int tr_job_head_id) {
		Connection conn= null;
		MapJobProblemDBModel mapJobProblemDBModel = null;
		
		try {
			LOGGER.info("Getting the connection from database manager");

			 conn=DatabaseManager.getConnection();
			PreparedStatement preparedStatement= conn.prepareStatement(JOB_PROBLEM_QUERY);
			preparedStatement.setInt(1, tr_job_head_id);
			LOGGER.info("Executing the SQL query {}",JOB_PROBLEM_QUERY);

		ResultSet resSet=	preparedStatement.executeQuery();
		
		while(resSet.next()) {
			
			 mapJobProblemDBModel = new MapJobProblemDBModel(resSet.getInt("mst_problem_id"),resSet.getString("remark"));
			
			
		}
		} catch (SQLException e) {
			LOGGER.error("cannot convert the result set to JOB Problem ",e);
			e.printStackTrace();
		}
		return mapJobProblemDBModel;
		
	}

}
