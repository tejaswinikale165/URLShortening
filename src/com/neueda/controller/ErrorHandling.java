package com.neueda.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ErrorHandling
 */
@WebServlet("/error")
public class ErrorHandling extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErrorHandling() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		System.out.println("status"+statusCode);
		        if(statusCode==401 || statusCode==403)
		        {
		        
		        request.setAttribute("errormsg", "Sorry, Authorization Issue.");
		        }
		        
		        else if(statusCode==404)
		        {
		        request.setAttribute("errormsg", request.getAttribute("javax.servlet.error.message"));
		        }
		        
		        else if(statusCode==500)
		        {
		        request.setAttribute("errormsg", "Sorry, Internal Server Error: Try after sometime.");
		        }
		        
		        //Default msg for all exception except above mentioned exceptions.
		        else {
		        request.setAttribute("errormsg", "Sorry, Internal Server Error: Try after sometime.");
		        }

		        request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
