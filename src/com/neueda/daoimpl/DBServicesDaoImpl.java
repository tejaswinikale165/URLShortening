package com.neueda.daoimpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.neueda.Llistener.DBListener;
import com.neueda.bean.Analysis;

import com.neueda.dao.DBservicesDao;

public  class DBServicesDaoImpl implements DBservicesDao{
	private static Connection conn;
	private static Statement statement;
	private static PreparedStatement prepStatement;
	//initialize data connection
	public DBServicesDaoImpl()
	{
		conn=DBListener.getConnection();
		
	}	
	
	//get id for the inserted longurl
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
	
	
	//get total clicks for the short url
	public ArrayList<Analysis> getAnalysisData(int userId) throws SQLException
	{
		statement =conn.createStatement();
		ArrayList<Analysis> ar=new ArrayList<Analysis>();
		ResultSet resultSet=statement.executeQuery("SELECT  id,longurl,hitcount,userid,urldate,urltime,  urlyear, urlday, urlhour from neueda.urlanalysisdw where userid="+userId);
		while(resultSet.next())
		{
			ar.add(new Analysis(resultSet.getInt(1),resultSet.getString(2), resultSet.getInt(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6), resultSet.getInt(7),resultSet.getInt(8),resultSet.getInt(9)));
		}
		return ar;
	}
	
	//get total clicks for the short url
	public int getTotalLinksCreated(int userId) throws SQLException
	{
		statement =conn.createStatement();
		
		ResultSet resultSet=statement.executeQuery("select count(longUrl) from urldetails where userid="+userId);
		if (resultSet.next())
		{
			return resultSet.getInt(1);
		}
		return 0;
	}
	
	
	
	
	//Get current date in format to insert in database datetime format
	public String getCurrDateTime()
	{
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		String currentDateTime = format.format(date);
		return currentDateTime;
	}
	//insert long url in database and get id for inserted long url. Encode the id to create short link 
	public int insertURL(String URL,int userId) throws SQLException {
		createTable();
		System.out.println("User id"+userId);
		 prepStatement=conn.prepareStatement("insert into neueda.urldetails(longURL,preshitcount,hrlyhitcount,userid,createddate)values(?,0,0,?,?)",Statement.RETURN_GENERATED_KEYS);
		 prepStatement.setString(1,URL);
		 prepStatement.setInt(2,userId);
		 prepStatement.setString(3,getCurrDateTime());
		int updatedNo=prepStatement.executeUpdate();
		
		if(updatedNo>0)
		{
			ResultSet tableKeys = prepStatement.getGeneratedKeys();
			tableKeys.next();
			 
			return tableKeys.getInt(1);
		}
		
		return 0;
		
	}
	
	//Retrieve long url from database depending on the id provided and update the click  
	public String getLongerURL(String id) throws SQLException {
		System.out.println("Database Id:"+ id);
		
		prepStatement=conn.prepareStatement("SELECT longURL, preshitcount,hrlyhitcount from neueda.urldetails where id=?");
		prepStatement.setString(1,id);
		ResultSet resultSet=prepStatement.executeQuery();
		if(resultSet.next())
		{
			int preshitcount=resultSet.getInt(2)+1;
			int hrlyhitcount=resultSet.getInt(3)+1;
			prepStatement=conn.prepareStatement("update neueda.urldetails set preshitcount=?,hrlyhitcount=? where id=?");
			prepStatement.setInt(1,preshitcount);
			prepStatement.setInt(2,hrlyhitcount);
			prepStatement.setString(3,id);
			prepStatement.executeUpdate();
			return resultSet.getString(1);
		}
		return "Not Found";
	}
	
	public void createTable() throws SQLException
	{
		statement =conn.createStatement();
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS neueda.urldetails ( id INT AUTO_INCREMENT PRIMARY KEY,longURL VARCHAR(255) NOT NULL, hitcount int not null)");
		
	}

	@Override
	public int getTotalClicks(int userId) throws SQLException{
		statement =conn.createStatement();
		
		ResultSet resultSet=statement.executeQuery("select sum(preshitcount) from neueda.urldetails where userid="+userId);
		if (resultSet.next())
		{
			return resultSet.getInt(1);
		}
		return 0;
	}	
	
	
	@Override
	public ArrayList<String> getUrlsByUser(int userId) throws SQLException{
		statement =conn.createStatement();
		ArrayList<String> urls=new ArrayList<String>();
		ResultSet resultSet=statement.executeQuery("select distinct(longurl) from urldetails where userid="+userId+" order by longurl desc");
		while (resultSet.next())
		{
			urls.add( resultSet.getString(1));
		}
		return urls;
	}	
	
	
	@Override
	public int avgClicksPerHr(int userId) throws SQLException{
		statement =conn.createStatement();
		
		ResultSet resultSet=statement.executeQuery("SELECT sum(hitcount)/count(urlhour) FROM neueda.urlanalysisdw where userid="+userId);
		if (resultSet.next())
		{
			return resultSet.getInt(1);
		}
		return 0;
	}

	@Override
	public ArrayList<Analysis> clicksByUrl(int userId) throws SQLException{
		ArrayList<Analysis> clicksByUrlList=new ArrayList<Analysis>();
		URLServicesDaoImpl service=new URLServicesDaoImpl();
		prepStatement=conn.prepareStatement("select id,longurl,sum(preshitcount) from neueda.urldetails where userid=? group by longurl,id ");
		prepStatement.setInt(1, userId);
		ResultSet resultSet=prepStatement.executeQuery();
		while (resultSet.next())
		{
			
			clicksByUrlList.add(new Analysis(service.encode(resultSet.getString(1)),resultSet.getString(2),resultSet.getInt(3)));
		}
		
		return clicksByUrlList;
	}
	
	@Override
	public ArrayList<Analysis> clicksByDate(int userId) throws SQLException{
		ArrayList<Analysis> clicksByDateList=new ArrayList<Analysis>();
		
		prepStatement=conn.prepareStatement("select urldate,sum(hitcount) from neueda.urlanalysisdw where userid=? group by urldate");
		prepStatement.setInt(1, userId);
		ResultSet resultSet=prepStatement.executeQuery();
		while (resultSet.next())
		{
			clicksByDateList.add(new Analysis(resultSet.getInt(2),resultSet.getString(1)));
		}
		
		return clicksByDateList;
	}

	@Override
	public ArrayList<Analysis> clicksByDateAndUrl(int userId) throws SQLException{
		ArrayList<Analysis> clicksByDateAndUrlList=new ArrayList<Analysis>();
		URLServicesDaoImpl service=new URLServicesDaoImpl();
		prepStatement=conn.prepareStatement("select id,longurl,urldate,sum(hitcount) from neueda.urlanalysisdw where userid=? group by urldate,longurl,id order by longurl desc");
		prepStatement.setInt(1, userId);
		ResultSet resultSet=prepStatement.executeQuery();
		while (resultSet.next())
		{
			clicksByDateAndUrlList.add(new Analysis(service.encode(resultSet.getString(1)),resultSet.getString(2),resultSet.getInt(4),resultSet.getString(3)));
		}
		
		return clicksByDateAndUrlList;
	}
	
	@Override
	public ArrayList<Analysis> clicksInFiveYears(int userId) throws SQLException{
		ArrayList<Analysis> clicksInFiveYearsList=new ArrayList<Analysis>();
		prepStatement=conn.prepareStatement("select urlyear,sum(hitcount) from neueda.urlanalysisdw where userid=? group by urlyear order by urlyear desc limit 5");
		prepStatement.setInt(1, userId);
		ResultSet resultSet=prepStatement.executeQuery();
		while (resultSet.next())
		{
			clicksInFiveYearsList.add(new Analysis(resultSet.getInt(2),resultSet.getInt(1)));
		}
		
		return clicksInFiveYearsList;
	}
	@Override
	public int insertUser(String username, String password) throws SQLException {
		prepStatement=conn.prepareStatement("insert into neueda.userdetails(email,password)values(?,?)");
		 prepStatement.setString(1,username);
		 prepStatement.setString(2,password);
		
		return prepStatement.executeUpdate();
		
	}

	@Override
    public int getUserId(String username, String password) throws SQLException {
	    	prepStatement=conn.prepareStatement("select id from neueda.userdetails where email=? and password=?");
			 prepStatement.setString(1,username);
			 prepStatement.setString(2,password);
			ResultSet rs= prepStatement.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
			}
			return 0;
	}
	
	
	
}
