package com.neueda.Llistener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
@WebListener
public class DBListener implements ServletContextListener{
	private static Connection connection ;
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context=event.getServletContext();
		String username= context.getInitParameter("username");
		String password=context.getInitParameter("password");
		String dbdriver=context.getInitParameter("dbdriver");
		String url= context.getInitParameter("url");
		String dbCheck="false";
		System.out.println("user: "+username+"url"+url);
		try {
			setConnection(username,password,dbdriver,url);
			dbCheck="true";
			context.setInitParameter("dbCheck", dbCheck);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			dbCheck="false";
			context.setInitParameter("dbCheck", dbCheck);
			System.out.println("dbChecked "+dbCheck);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			dbCheck="false";
			context.setInitParameter("dbCheck", dbCheck);
			System.out.println("dbChecked "+dbCheck);
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return connection;
	}
	public void setConnection(String username,String password,String dbdriver,String url) throws ClassNotFoundException, SQLException {
		
			Class.forName(dbdriver);
		
		Connection connection=DriverManager.getConnection(url, username,password);
		DBListener.connection = connection;
		System.out.println("---------sdfsdhfbsdhf------------"+connection);
		
	}
	

}
