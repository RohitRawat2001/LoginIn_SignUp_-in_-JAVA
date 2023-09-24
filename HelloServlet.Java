package com;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/hello")

public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
						HttpServletResponse response)
		throws ServletException, IOException
	{

		
		String uemail = request.getParameter("email");
		String upassword = request.getParameter("password");
		
		
		try {
			PrintWriter out=response.getWriter();
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection(
		                "jdbc:mysql://localhost:3306/servlet",
		                "root", "rohit@2001");
			 PreparedStatement pst=con.prepareStatement("select * from users where uemail = ? and upassword = ? ");
						 
			 
			 pst.setString(1, uemail);
			 pst.setString(2, upassword);
			 
			 
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
	      RequestDispatcher rd = request.getRequestDispatcher("LoginSuccess.html");
	      rd.forward(request, response);
		}
		else {
			out.println("<font color=red size=18>Login Failed!!<br>");
			out.println("<a href=index.html>Try Again!!</a>");
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

