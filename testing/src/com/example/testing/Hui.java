package com.example.testing;

import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Hui extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException
	{
		 try
         
         {   
             Class.forName("com.mysql.jdbc.GoogleDriver");
             System.out.println("hello i am here sai susheel");
           //  Connection c=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
             Connection c=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
             System.out.println("hii susheel");
             Statement s=c.createStatement();
              System.out.println("hii susheel in cse2");
             //boolean ss=s.execute("insert ini to admins values('"+id_text+"','"+name_text+"','"+pass_text+"','"+domain_text+"')");                
              boolean ss=s.execute("insert into admins values('aaa','aa33','ssss','dddd')");
             resp.getWriter().println("DAMN inserted");
              //s.executeUpdate(sid)
         }
		 catch(Exception e)
		 {
			// System.out.println(""+e);
			 resp.getWriter().println(""+e);
		 }
	}

}
