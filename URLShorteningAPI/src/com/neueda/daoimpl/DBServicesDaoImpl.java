package com.neueda.daoimpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.neueda.Llistener.DBListener;
import com.neueda.dao.DBservicesDao;

public  class DBServicesDaoImpl implements DBservicesDao{
	private static Connection conn;
	private static Statement statement;
	private static PreparedStatement prepStatement;
	
	public DBServicesDaoImpl()
	{
		conn=DBListener.getConnection();
	}	
	
	
	public String getId() throws SQLException
	{
		statement =conn.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT LAST_INSERT_ID()");
		if(resultSet.next())
		{
			
			return String.valueOf(resultSet.getInt(1));
		}
		return null;
	}
	
	public String insertURL(String URL) throws SQLException {
		createTable();
		 prepStatement=conn.prepareStatement("insert into urldetails(longURL)values(?)");
		
		 prepStatement.setString(1,URL);
		int updatedNo=prepStatement.executeUpdate();
		
		if(updatedNo>0)
		{
			return getId();
		}
		
		return null;
		
	}
	
	public String getLongerURL(String Id) throws SQLException {
		System.out.println("Database Id:"+ Id);
		createTable();
		prepStatement=conn.prepareStatement("SELECT longURL from urldetails where id=?");
		prepStatement.setString(1,Id);
		ResultSet resultSet=prepStatement.executeQuery();
		if(resultSet.next())
		{
			
			return resultSet.getString(1);
		}
		return null;
	}
	
	public void createTable() throws SQLException
	{
		
		statement =conn.createStatement();
		statement.execute("create table IF NOT EXISTS neueda.urldetails ( id INT AUTO_INCREMENT PRIMARY KEY,longURL VARCHAR(255) NOT NULL);\n" + 
				"");	}	
	
}
