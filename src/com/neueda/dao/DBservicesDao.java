package com.neueda.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.neueda.bean.Analysis;


public interface DBservicesDao {
	public String getId() throws SQLException;
	public int insertURL(String URL,int userId) throws SQLException ;
	public String getLongerURL(String Id) throws SQLException;
	public ArrayList<Analysis> getAnalysisData(int userId)throws SQLException;
	public int getTotalLinksCreated(int userId) throws SQLException;
	public int getTotalClicks(int userId) throws SQLException;
	int avgClicksPerHr(int userId) throws SQLException;
	public ArrayList<Analysis> clicksByUrl(int userId) throws SQLException;
	ArrayList<Analysis> clicksByDate(int userId) throws SQLException;
	public ArrayList<Analysis> clicksByDateAndUrl(int userId) throws SQLException;
	ArrayList<Analysis> clicksInFiveYears(int userId) throws SQLException;
	ArrayList<String> getUrlsByUser(int userId) throws SQLException;
	int insertUser(String username, String password) throws SQLException;
	int getUserId(String username, String password) throws SQLException;
}
