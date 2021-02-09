package com.neueda.dao;

import java.sql.SQLException;

public interface DBservicesDao {
	public String getId() throws SQLException;
	public String insertURL(String URL) throws SQLException ;
	public String getLongerURL(String Id) throws SQLException;

}
