package com.neueda.daoimpl;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.neueda.dao.AuthenticationServicesDao;

public class AuthenticationServicesDaoImpl implements AuthenticationServicesDao {
	DBServicesDaoImpl dbServices;
	public AuthenticationServicesDaoImpl()
	{
		dbServices=new DBServicesDaoImpl();
	}
	@Override
	public int validate(String username, String password) throws SQLIntegrityConstraintViolationException,SQLException {
		
		return dbServices.getUserId(username,password);
	}
	
	@Override
	public int signUp(String username, String password) throws  SQLIntegrityConstraintViolationException,SQLException {
		
		return dbServices.insertUser(username,password);
	}
}
