package com.neueda.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import com.neueda.bean.Analysis;


public interface AnalysisServicesDao {

	ArrayList<Analysis> getAnalysis(int userId) throws SQLException;

	
	int getTotalLinksCreated(int userId) throws SQLException;


	int getTotalClicks(int userId) throws SQLException;


	int avgClicksPerHr(int userId) throws SQLException;


	ArrayList<Analysis> clicksByUrl(int userId) throws SQLException;


	ArrayList<Analysis> clicksByDate(int userId) throws SQLException;


	ArrayList<Analysis> clicksByDateAndUrl(int userId) throws SQLException;
	
	ArrayList<Analysis> clicksInFiveYears(int userId) throws SQLException;


	public String barClicksByUrl(int userId, String shortUrlData) throws SQLException;


	String lineClicksByDate(int userId) throws SQLException;


	String lineByDateUrl(int userId) throws SQLException, ParseException;


	String doughnutChart(int userId) throws SQLException;

}
