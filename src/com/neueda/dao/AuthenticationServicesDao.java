package com.neueda.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public interface AuthenticationServicesDao {

	int validate(String parameter, String parameter2) throws SQLException;

	int signUp(String username, String password) throws  SQLIntegrityConstraintViolationException, SQLException;

}
