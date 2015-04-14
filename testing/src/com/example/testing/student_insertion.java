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

/**
 *
 * @author chimmu123
 */
//@WebServlet(urlPatterns = {"/student_check"})
public class student_insertion extends HttpServlet {

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
           /* out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet student_check</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet student_check at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
            String admin1="",admin2="",admin3="";
        //    Connection ck,c1,c2,c3;
            String a=request.getParameter("name");
            String b=request.getParameter("id");
            String c=request.getParameter("regId");
               String d=request.getParameter("password");
            String e=(request.getParameter("class")).trim();
            String f=(request.getParameter("year")).trim();
             try
            {   
                Class.forName("com.mysql.jdbc.GoogleDriver");
              Connection  ck=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
               
              Statement sk2=ck.createStatement();
              ResultSet ef=sk2.executeQuery(" select * from admins where cd='CLG' ");
              boolean ct=ef.next();
              if(ct)
              {
            	  admin3=ef.getString("id");
              }
             
              
              ck.close();
              Connection  ck1=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
              
              Statement sk=ck1.createStatement();
                ResultSet ab=sk.executeQuery("select * from admins where cd='"+e+"' and year='"+f+"'"); 
                  boolean check= ab.next();
                if(check)
                {
                	  admin1=ab.getString("id");
                }
                
                  ck1.close();
                  Connection  ck2=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
                  Statement sk1=ck2.createStatement();
                  ResultSet cd=sk1.executeQuery(" select * from admins where cd=substr('"+e+"',1,length('"+e+"')-1) and year='"+f+"' ");
                  boolean cr=cd.next();
                  if(cr)
                  {
                	  admin2=cd.getString("id");
                  }
                 
                  ck2.close();
              /*    Statement sk2=ck.createStatement();
                  ResultSet ef=sk2.executeQuery(" select * from admins where cd='CLG' ");
                  boolean ct=ef.next();
                  admin3=ef.getString("id");*/
                  
            //    response.setHeader("me done",aa);
                System.out.println("hello!!!");
               
              
               
                ab.close();
                cd.close();
                ef.close();
              

            }
             
              catch(Exception e1)
              {
            	  out.println(""+e1);
              }
             
              try
            {   
                Class.forName("com.mysql.jdbc.GoogleDriver");
               Connection c1=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
                Statement s=c1.createStatement();
                boolean ss=s.execute("delete from students where id='"+b+"'");
                System.out.println(""+ss);
                
            //    response.setHeader("me done",aa);
                System.out.println("hello!!!");
                c1.close();
                

            }
              catch(Exception e2)
              {
            	  out.println(""+e2);
              }
              try
            {   
                Class.forName("com.mysql.jdbc.GoogleDriver");
              Connection  c2=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
                Statement s1=c2.createStatement();
                boolean ss1=s1.execute("insert into students values('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"')");    
                
            //    response.setHeader("me done",aa);
                System.out.println("hello!!!");
                    c2.close();
            }
              catch(Exception e1)
              {
              }
              
              try
              {   
                  Class.forName("com.mysql.jdbc.GoogleDriver");
                 Connection c4=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
                  Statement s3=c4.createStatement();
                  boolean ss1=s3.execute("delete from reg_keys where id='"+b+"'");
                  System.out.println(""+ss1);
                  
              //    response.setHeader("me done",aa);
                  System.out.println("hello!!!");
                  c4.close();
                  

              }
                catch(Exception e22)
                {
                	out.println(""+e22);
                }
              
              try
              {   
                  Class.forName("com.mysql.jdbc.GoogleDriver");
                 Connection c5=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
                  Statement s4=c5.createStatement();
                  boolean ss1=s4.execute("delete from reg_keys where reg_id='"+c+"'");
                  System.out.println(""+ss1);
                  
              //    response.setHeader("me done",aa);
                  System.out.println("hello!!!");
                  c5.close();
                  

              }
                catch(Exception e11)
                {
                	out.println(""+e11);
                }
               try
            {   
                Class.forName("com.mysql.jdbc.GoogleDriver");
               Connection c3=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
                Statement s2=c3.createStatement();
                boolean ss2=s2.execute("insert into reg_keys values('"+b+"','"+c+"','"+e+"','"+f+"','"+admin1+"','"+admin2+"','"+admin3+"')");    
                
            //    response.setHeader("me done",aa);
                System.out.println("hello!!!");
                c3.close();

            }
              catch(Exception e1)
              {
            	  out.println(""+e1);
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

