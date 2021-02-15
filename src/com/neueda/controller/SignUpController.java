package com.neueda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neueda.dao.AuthenticationServicesDao;
import com.neueda.daoimpl.AuthenticationServicesDaoImpl;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet("/signup")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthenticationServicesDao authenticationServices=new AuthenticationServicesDaoImpl();
		//RequestDispatcher rd =request.getRequestDispatcher("/error");
		PrintWriter out = response.getWriter();
		try {
			int complete=authenticationServices.signUp(request.getParameter("username"),request.getParameter("password"));
			System.out.println(complete);
			if(complete==0)
			{
				//response.setStatus(500);
				RequestDispatcher rd =request.getRequestDispatcher("/error");
				rd.forward(request, response);
			}
			else {
				out.print("Succesfully Signed Up. Please login");
				RequestDispatcher rd =request.getRequestDispatcher("/login");
				rd.include(request, response);
				
			}
			
		} catch (SQLIntegrityConstraintViolationException e) {
			
	       
			RequestDispatcher rd =request.getRequestDispatcher("/login.jsp");
			rd.include(request, response);
			out.println("<div class='div1'><center>Username(email) already exist.</center></div>");
	
		} 
		catch (SQLException e) {
			
	        request.setAttribute("errormsg", "Sorry, Internal Server Error: Try after sometime.");
			RequestDispatcher rd =request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
	
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
