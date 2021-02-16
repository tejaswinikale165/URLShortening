package com.neueda.testing;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.Before;
import org.junit.Test;

import com.neueda.daoimpl.AuthenticationServicesDaoImpl;

public class UnitTesting {
	AuthenticationServicesDaoImpl authServices;
	  @Before                                         
	    public void setUp() throws Exception {
		  authServices = new AuthenticationServicesDaoImpl();
	    }
	
	@Test
	   public void testPrintMessage() throws SQLIntegrityConstraintViolationException, SQLException {	
	     int userId=authServices.validate("notuser", "notuser") ;
	      assertEquals(userId, 1);     
	   }

}
