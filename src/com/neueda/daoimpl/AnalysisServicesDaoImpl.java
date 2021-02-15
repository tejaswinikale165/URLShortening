package com.neueda.daoimpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.neueda.bean.Analysis;
import com.neueda.dao.AnalysisServicesDao;
import com.neueda.dao.DBservicesDao;

public class AnalysisServicesDaoImpl implements AnalysisServicesDao {
	DBservicesDao dbServices;
	public AnalysisServicesDaoImpl() {
		dbServices=new DBServicesDaoImpl();
	}
	@Override
	public ArrayList<Analysis> getAnalysis(int userId) throws SQLException {
		return dbServices.getAnalysisData(userId);

	}

	@Override
	public int getTotalLinksCreated(int userId) throws SQLException {
		return dbServices.getTotalLinksCreated(userId);

	}

	@Override
	public int getTotalClicks(int userId) throws SQLException {
		return dbServices.getTotalClicks(userId);

	}

	@Override
	public int avgClicksPerHr(int userId) throws SQLException {
		return dbServices.avgClicksPerHr(userId);

	}

	@Override
	public ArrayList<Analysis> clicksByUrl(int userId) throws SQLException {

		return dbServices.clicksByUrl(userId);

	}

	@Override
	public ArrayList<Analysis> clicksByDate(int userId) throws SQLException
	{
		return dbServices.clicksByDate(userId);
	}

	@Override
	public ArrayList<Analysis> clicksByDateAndUrl(int userId) throws SQLException
	{
		return dbServices.clicksByDateAndUrl(userId);
	}
	@Override
	public ArrayList<Analysis> clicksInFiveYears(int userId) throws SQLException {
		return dbServices.clicksInFiveYears(userId);
	}

	@Override
	public String barClicksByUrl(int userId, String shortUrlData) throws SQLException
	{
		ArrayList<Analysis>  clicksByUrl=clicksByUrl( userId);
		Gson gsonObj = new Gson();

		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

		for(int i=0;i<clicksByUrl.size();i++)
		{
			map = new HashMap<Object,Object>(); 

			map.put("y", clicksByUrl.get(i).getHitCount()); 
			map.put("label", shortUrlData+clicksByUrl.get(i).getShortUrl()); 
			list.add(map);

		}

		return gsonObj.toJson(list);

	}

	@Override
	public String lineClicksByDate(int userId) throws SQLException
	{
		ArrayList<Analysis>  clicksByDate=clicksByDate( userId);
		Gson gsonObj = new Gson();

		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

		for(int i=0;i<clicksByDate.size();i++)
		{
			map = new HashMap<Object,Object>(); 

			map.put("y", clicksByDate.get(i).getHitCount()); 
			map.put("label", clicksByDate.get(i).getUrlDate()); 
			list.add(map);

		}

		return gsonObj.toJson(list);

	}
	
	@Override
	public String doughnutChart(int userId) throws SQLException
	{
		ArrayList<Analysis>  clicksInFiveYears=clicksInFiveYears( userId);
		Gson gsonObj = new Gson();

		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
        String color[]=new String[]{"#283963","#036080","#007573","#46634a","#948503"};
		for(int i=0;i<clicksInFiveYears.size();i++)
		{
			map = new HashMap<Object,Object>(); 

			map.put("y", clicksInFiveYears.get(i).getHitCount()); 
			map.put("name", String.valueOf(clicksInFiveYears.get(i).getUrlYear())); 
			map.put("color",color[i]);
			//map.put("legendText",clicksInFiveYears.get(i).getUrlYear());
			list.add(map);

		}
		System.out.println(gsonObj.toJson(list));

		return gsonObj.toJson(list);

	}

	@Override
	public String lineByDateUrl(int userId) throws SQLException, ParseException
	{
		ArrayList<Analysis>  clicksByDate=clicksByDateAndUrl( userId);
		ArrayList<String> urlsByUser=dbServices.getUrlsByUser(userId);

		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		Map<Object,Object> datapointmap = null;

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
       
		
		for (int i =0; i<urlsByUser.size();i++)
		{
			ArrayList<Object>datapoints=new ArrayList<Object>();
			map = new HashMap<Object,Object>(); 

			for(int j=0;j<clicksByDate.size();j++)
			{
				if(clicksByDate.get(j).getLongUrl().equals(urlsByUser.get(i)))
				{
					Date date = formatter.parse(clicksByDate.get(j).getUrlDate());

					datapointmap = new HashMap<Object,Object>(); 
					datapointmap.put("label",clicksByDate.get(j).getUrlDate() );
					datapointmap.put("y",clicksByDate.get(j).getHitCount() );

					datapoints.add(datapointmap);
					map.put("dataPoints", datapoints);
				}

			}
			map.put("showInLegend", true);
			map.put("type", "line");
			map.put("name", urlsByUser.get(i));
			
			
			list.add(map);
		}



		Gson gsonObj = new Gson();

		System.out.println(gsonObj.toJson(list));
		return gsonObj.toJson(list);

	}
}
