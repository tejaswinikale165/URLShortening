package com.neueda.dao;

import java.sql.SQLException;

public interface URLServicesDao {
 public String getShortenedURL(String URL) throws SQLException;
 public String getLongerURL(String Id) throws SQLException;
 public boolean isValidURL(String url);
 
}
