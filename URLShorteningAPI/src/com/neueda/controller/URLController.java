package com.neueda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neueda.dao.URLServicesDao;
import com.neueda.daoimpl.DBServicesDaoImpl;
import com.neueda.daoimpl.URLServicesDaoImpl;

/**
 * Servlet implementation class URLController
 */
@WebServlet("/short")
public class URLController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public URLController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		if("false".equals(request.getServletContext().getInitParameter("dbCheck")))
		{
			response.sendError(500,"Database issue");
			return;
		}

		String longURL=request.getParameter("longurl");
		URLServicesDao services=new URLServicesDaoImpl();

		if(services.isValidURL(longURL)) {

			try {
				String shortURL=services.getShortenedURL(longURL);
				if(shortURL==null)
				{
					
					response.sendError(500, "Database issue");
					return;
				}

				shortURL = "http://"+request.getServerName()+":"+request.getServerPort()+"/"+shortURL;
				request.setAttribute("shorturl", shortURL);
				RequestDispatcher rd =request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
				out.println("<table style=\"width:100%\" id=\"urltable\"style=\"width:50%;\">\n" + 
						"  <tr>\n" + 
						"    <th>URL</th>\n" + 
						"    <th>Short URL</th>\n" + 
						"  </tr>\n" + 
						"  <tr>\n" + 
						"    <td>"+longURL+ "</td>\n" + 
						"    <td><a href=\""+shortURL+"\">"+shortURL+"</a></td>\n" + 
						"  </tr>");


			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{

			RequestDispatcher rd =request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
			out.println("<div class='div1'><center>Invalid URL: Format (http://www.google.com)</center><div>");

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
