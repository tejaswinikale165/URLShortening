package com.neueda.Llistener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		
		try {
			setConnection(username,password,dbdriver,url);
			//executeInitialScript();
			System.out.println("------dconne------------- "+connection);
			dbCheck="true";
			context.setInitParameter("dbCheck", dbCheck);
		} catch (ClassNotFoundException e) {
			
			dbCheck="false";
			context.setInitParameter("dbCheck", dbCheck);
			System.out.println("dbChecked "+dbCheck);
			e.printStackTrace();
		} catch (SQLException e) {
			
			dbCheck="false";
			context.setInitParameter("dbCheck", dbCheck);
			System.out.println("------dbChecked------------- "+dbCheck);
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
		
		
	}
	
	private void executeInitialScript() throws SQLException
	{
		Statement statement;
		String databsename="use neueda";
       statement =connection.createStatement();
		ResultSet resultSet=statement.executeQuery(databsename);
		String usertable="CREATE TABLE neueda.userdetails (id INT AUTO_INCREMENT,email VARCHAR(100) NOT NULL,password VARCHAR(45) NOT NULL, PRIMARY KEY (id),UNIQUE (email) )";
		statement.executeUpdate(usertable);
		String urltable="create table neueda.urldetails ( id INT AUTO_INCREMENT PRIMARY KEY,longURL VARCHAR(255) NOT NULL,preshitcount int,hrlyhitcount int,userid int,createddate datetime not null,FOREIGN KEY (userid) REFERENCES userdetails(id) )";
		statement.executeUpdate(urltable);
		  String urlanalysisdw="create table neueda.urlanalysisdw ( id INT ,longURL VARCHAR(255) NOT NULL,hitcount int,userid int,urldate date,urltime time,urlyear int, urlday  int,urlhour int)";
		 statement.executeUpdate(urlanalysisdw);
		 
		String event =" delimiter | CREATE EVENT IF NOT EXISTS reurring_event "
				+ "ON SCHEDULE EVERY 1 hour STARTS CURRENT_TIMESTAMP "
				+ "DO "
				+ "begin"
				+ " INSERT INTO neueda.urlanalysisdw (  id,  longurl,  hitcount, "+
		      "userid,"+
		     " urldate,"+
		     " urltime,"+
		      "urlyear,"+
		     " urlday,"+
		     " urlhour) "+
		"SELECT id, "+
		  "     longurl,"+ 
		    "   hrlyhitcount, "+
		     "  userid,"+
		      " DATE(now()),"+
		      " time(now()),"+
		      " year(now()),"+
		       "day(now()),"+
		       "hour(now())"+
		"FROM neueda.urldetails"+
		"WHERE userid != 1;"+
		"update neueda.urldetails set hrlyhitcount=0 where userid!=1;"+
		 "END |"
		 + "delimiter ;";
		
		
		statement.executeUpdate(event);
		
		String user= "insert into neueda.userdetails (email,password) values('notuser','notuser')";
		statement.executeUpdate(user);
	}
	

}
