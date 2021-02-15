package com.neueda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neueda.bean.Analysis;
import com.neueda.dao.AnalysisServicesDao;
import com.neueda.daoimpl.AnalysisServicesDaoImpl;

@WebServlet("/analysis")
public class AnalysisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AnalysisController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AnalysisServicesDao analysisServices=new AnalysisServicesDaoImpl();
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if(session.getAttribute("userid")== null)
		{
			RequestDispatcher rd =request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		else {
			Integer userid = Integer.parseInt((String) session.getAttribute("userid"));
		String shortUrlData="http://"+request.getServerName()+":"+request.getServerPort()+"/";
		try {
			int noUrlCreated = analysisServices.getTotalLinksCreated(userid);
			
			
			request.setAttribute("nourlcreated", noUrlCreated);
			int noClicks = analysisServices.getTotalClicks(userid);
			request.setAttribute("noclicks", noClicks);
			int avgClicksPerHr = analysisServices.avgClicksPerHr(userid);
			request.setAttribute("avgclicksperhr", avgClicksPerHr);
			
			String lineClicksByDate=analysisServices.lineClicksByDate(userid);
			request.setAttribute("lineclicksbydate", lineClicksByDate);
			
			String lineByDateUrl=analysisServices.lineByDateUrl(userid);
			request.setAttribute("linebydateurl", lineByDateUrl);
			
			String barClicksByUrl=analysisServices.barClicksByUrl(userid,shortUrlData);
			request.setAttribute("barclicksbyurl", barClicksByUrl);
			
			
			String doughnutChart=analysisServices.doughnutChart(userid);
			request.setAttribute("doughnutchart", doughnutChart);
			
			RequestDispatcher rd= request.getRequestDispatcher("analysis.jsp");
			rd.forward(request, response);
		} catch (SQLException | ParseException e) {
			request.setAttribute("errormsg", "Sorry, Internal Server Error: Try after sometime.");
			RequestDispatcher rd =request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
