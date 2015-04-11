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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chimmu123
 */
public class student_validation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /* String serverName = "localhost"; 
String portNumber = "1521"; 
String sid = "mproject"; 
 
String url="jdbc:oracle:thin:@"+serverName+":"+ portNumber+":"+sid; 

 
String username = "SYS AS SYSDBA"; 
String password = "susheel786"; */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            /* TODO output your page here. You may use following sample code. */
         /*   out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet student_validation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet student_validation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
              String b=request.getParameter("id");
                  String d=request.getParameter("password");
                  String c;
                     try
            {   
                Class.forName("com.mysql.jdbc.GoogleDriver");
              Connection  ck=DriverManager.getConnection("jdbc:google:mysql://pr-server:project-server/m_project?user=root");
                Statement sk=ck.createStatement();
                ResultSet ab=sk.executeQuery("select * from students where id='"+b+"'"); 
                  boolean check= ab.next();
                    c=ab.getString("pass");
                   
                    
                     ck.close();
                    // still there..........to send response message
                    if(c.equals(d))
                    {
                    	out.println("verified");
                    	response.setHeader("login-checking", "verified");
                    return;
                    }
                    else
                    {
                    	out.println("not verified");
                        response.setHeader("login-checking","not-verified");
                    return;
                    }
                    
                    
                
               /*   admin1=ab.getString("id");
                  ResultSet cd=sk.executeQuery("select * from admins where cd=substr('"+e+"',1,3)");
                  boolean cr=cd.next();
                  admin2=cd.getString("id");
                  ResultSet ef=sk.executeQuery("select * from admins where domain='clg'");
                  boolean ct=ef.next();
                  admin3=ef.getString("id");*/
                  
            //    response.setHeader("me done",aa);
             //   System.out.println("hello!!!");
               
              //  cd.close();
                //ef.close();
              

            }
             
              catch(Exception e1)
              {
            	  out.println("not verified");
                  System.out.println(""+e1);
              }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
