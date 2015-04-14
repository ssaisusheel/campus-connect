package com.example.testing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;  
import javax.servlet.http.*;  
  

/**
 *
 * @author chimmu123
 */
//@WebServlet(urlPatterns = {"/testing"})
public class testing1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   //  String serverName = "localhost"; 
//String portNumber = "1521"; 
//String sid = "project"; 
 
//String url="jdbc:oracle:thin:@"+serverName+":"+ portNumber+":"+sid; 

 
//String username = "SYS AS SYSDBA"; 
//String password = "susheel786"; 
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection c; Statement s; ResultSet rs;String jk="";String gg="";
        
        try (PrintWriter out = response.getWriter()) {
        	
        /*	out.println("<html>");
        	out.println("<body>");
        	out.println("<h1>hello</h1>");
        	out.println("</body>");
        	out.println("</html>");*/
            /* TODO output your page here. You may use following sample code. */
          /*  out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testing</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet testing at hello 123 " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
          //  out.println("<!--A Design by W3layouts \n" +");
              String i=request.getParameter("text_id");
                String j=request.getParameter("text_password");
                
                if(i.equals("")||j.equals(""))
                {
                     RequestDispatcher rd1=request.getRequestDispatcher("/error.html");  
		try{
		rd1.forward(request, response);  
		}
		catch(Exception e)
		{
		 System.out.println(e);	
		}
                }
                String g="";
            System.out.println("passing through servlet"+"   "+i);
            System.out.println(i);
            System.out.println(j);
              try
            {   
           //    out.println("hello");
            	  Class.forName("com.mysql.jdbc.GoogleDriver");
                 c=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
                 s=c.createStatement();
               // boolean ss=s.execute("select * from admins where id='"+i+"'");                
           //     response.setHeader("me done",aa);
               String kp=i.trim(); //when read from textfield some extraspace is being added...that is removed here
                String query="select * from admins where id='"+kp+"'";
                  rs=s.executeQuery(query);
                boolean check= rs.next();
               out.println(""+check);
            
                if(!check)
                {
                     RequestDispatcher rd=request.getRequestDispatcher("/error.html");  
		try{
		rd.forward(request, response);  
		}
		catch(Exception e)
		{
			out.println(e.toString()+"no adminsss");
		}
                }
                else
                {
               // {
                    g= rs.getString("pass");
                    jk=rs.getString("domain");
                    HttpSession sess=request.getSession();
                    sess.setAttribute("domain",jk);
                   gg= rs.getString("cd");
               //    out.println(""+jk);
                    request.setAttribute("ppp",jk);
                    request.setAttribute("id", kp);
                    request.setAttribute("cd",gg );
                if(j.equals(g))
                 {
                   // System.out.println(""+g);
                     RequestDispatcher rd1=request.getRequestDispatcher("/message.jsp");  
		try{
		rd1.forward(request, response);  
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			out.println(e.toString()+" "+"after verification");
		}
              
                 }
                 
                   else
                 {
                       RequestDispatcher rd1=request.getRequestDispatcher("/error.html");  
		try{
		rd1.forward(request, response);  
		}
		catch(Exception e)
		{
		// System.out.println(e);	
			out.println(e.toString()+"pass wrong");
		}
                 }
                     }    
                c.close();
                 //}
            
                System.out.println("hello!!!");

            }
              catch(Exception e)
              {
                 // System.out.println(e);
            	  out.println(e.toString()+"last catch");
              }
          
          /*  RequestDispatcher rd=request.getRequestDispatcher("/message.html");  
		try{
		rd.forward(request, response);  
		}
		catch(Exception e)
		{
			
		}*/
           // RequestDispatcher rd = request.getRequestDispatcher("/message.html");
//rd.forward(request, response);
             //   c.close();
        }
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
