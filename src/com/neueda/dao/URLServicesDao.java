package com.neueda.dao;

import java.sql.SQLException;

public interface URLServicesDao {
 
 public String getLongerURL(String Id) throws SQLException;
 public boolean isValidURL(String url);
 public String getShortenedURL(String longURL, int userId) throws SQLException;
 
}
