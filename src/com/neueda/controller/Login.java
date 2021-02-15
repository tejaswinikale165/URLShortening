package com.neueda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neueda.dao.AuthenticationServicesDao;
import com.neueda.daoimpl.AuthenticationServicesDaoImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if(session.getAttribute("userid")!= null)
		{
			RequestDispatcher rd =request.getRequestDispatcher("/analysis");
			rd.forward(request, response);
		}
		else {
			AuthenticationServicesDao authenticationServices=new AuthenticationServicesDaoImpl();

			int validation=0;
			try {
				validation = authenticationServices.validate(request.getParameter("username"),request.getParameter("password"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(validation !=0)
			{
				session.setAttribute("username", request.getParameter("username"));
				session.setAttribute("userid", String.valueOf(validation));
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30*60);
				Cookie userName = new Cookie("userid", String.valueOf(validation));
				userName.setMaxAge(30*60);
				response.addCookie(userName);
				
				RequestDispatcher rd =request.getRequestDispatcher("/analysis");
				rd.include(request, response);
			}
			
			else
			{
				RequestDispatcher rd =request.getRequestDispatcher("/login.jsp");
				rd.include(request, response);
				out.println("<div class='div1'><center>Invalid Username or Password");

			}

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
