package com.example.testing;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
@SuppressWarnings("serial")
public class TestingServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		//resp.getWriter().println("Hello, world");
		PrintWriter out = resp.getWriter();
		 //out.println("");
		 
		RequestDispatcher rd=req.getRequestDispatcher("/message.html");  
		try{
		/*	Class.forName("java,sql.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://173.194.245.35:3306/sample_14_02_2015","root","mainproject123");
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("select * from empl");
			while(rs.next())
			{
				out.println(rs.getString(1)+"--"+rs.getString(2)+"--"+rs.getDouble(3)+"--"+rs.getInt(4));
			}*/
			
		rd.forward(req, resp);  
		}
		catch(Exception e)
		{
			
		}
		 
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		//resp.getWriter().println("Hello, world");
		PrintWriter out = resp.getWriter();
		 //out.println("");
		 
		//RequestDispatcher rd=req.getRequestDispatcher("/message.html");  
		try{
			Class.forName("com.mysql.jdbc.GoogleDriver");
		Connection c=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/sample_14_02_2015?user=root");
	Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("select * from empl");
			while(rs.next())
			{
				out.println(rs.getString(1)+"--"+rs.getString(2)+"--"+rs.getDouble(3)+"--"+rs.getInt(4));
			}
			
			rs.close();
			s.close();
			c.close();
			
		//rd.forward(req, resp);  
		}
		catch(Exception e)
		{
			out.println(e.toString());
		}
		 
	}
}