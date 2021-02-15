package com.neueda.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neueda.dao.URLServicesDao;
import com.neueda.daoimpl.URLServicesDaoImpl;

/**
 * Servlet implementation class URLRdirectController
 */
@WebServlet("/")
public class URLRdirectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    boolean dbCheck;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public URLRdirectController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		URLServicesDao services=new URLServicesDaoImpl();
		if("false".equals(request.getServletContext().getInitParameter("dbCheck")))
		{
			response.sendError(500,"Database issue");
			return;
		}
		try {
			String longURL = services.getLongerURL(path.substring(1));
			if(longURL.equals("Not Found"))
			{
				response.sendError(404,"Invalid URL request. Check the URL");
				return;
			}
			response.sendRedirect(longURL);
		} catch (SQLException e) {
			request.setAttribute("errormsg", "Sorry, Internal Server Error: Try after sometime.");
			RequestDispatcher rd =request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
