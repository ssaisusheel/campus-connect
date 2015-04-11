package com.example.testing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 *
 * @author TOSHIBA
 */
public class keyreceiver extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  /*  String serverName = "localhost"; 
String portNumber = "1521"; 
String sid = "project"; 
 
String url="jdbc:oracle:thin:@"+serverName+":"+ portNumber+":"+sid; 

 
String username = "SYS AS SYSDBA"; 
String password = "susheel786"; */
 
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
	PrintWriter out=null;
       {
	out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
           //need http://ip:8080/WebApplication2/KeyReceiver?KEY_ID=<your app id> form of request from the app to be sent 
            String aa=request.getParameter("regId");
          //  String appName=(String)request.getParameter("APP_NAME");
      //      System.out.println("entered into keyreeiver");
            System.out.println("enterd keyreceiver");
            String gk="";
            int df=3;
            if(aa!=null)                
            try
            {   
                Class.forName("com.mysql.jdbc.GoogleDriver");
                Connection c=DriverManager.getConnection("jdbc:google:mysql://pr-server:project-server/m_project?user=root");
                Statement s=c.createStatement();
                boolean ss=s.execute("insert into reg_keys values('"+aa+"','"+gk+"','"+df+"','"+gk+"','"+gk+"','"+gk+"')");                
              //  response.setHeader("me done",aa);
                System.out.println("inserted into db");

            }
            catch(Exception e){
                
                 // response.setHeader("me done", e.toString());
                  System.out.println("dis is worng"+e.toString());
                  

            }   
            else
                try
                {
                   /* String id=request.getParameter("regId");
                    System.out.println(id);
                    if(id!=null)
                    {
                        File f=new File("C:\\regIDS.dat");
                        System.out.println(id);
                       //  File f=new File("regIDS.dat");
                        if(f.exists()==false)
                        {   
                            PrintWriter pr=new PrintWriter(new FileOutputStream("C:\\regIDS.dat"),true);
                                System.out.println("creating file");
                            // PrintWriter pr=new PrintWriter(new FileOutputStream("regIDS.dat"),true);
                            pr.println(id);
                        }
                        else
                        {
                            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("C:\\regIDS.dat")));
                           // BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("regIDS.dat")));
                               out.println("<html><body><p>");
                               String buff="";
                                java.util.HashSet<String> set=new java.util.HashSet<String>();
                                    
                                while((buff=br.readLine())!=null)
                                {
                                   
                                    set.add(buff);
                                    out.println(buff+"</br>");
                                       System.out.println("already derrre");
                                }
                                if(set.contains(id)==false)
                                {
                                       PrintWriter pr=new PrintWriter(new FileOutputStream("C:\\regIDS.dat",true),true);
                                       // PrintWriter pr=new PrintWriter(new FileOutputStream("regIDS.dat",true),true);
                                        pr.println(id);
                                        System.out.println("wrote here");
                                }
                                
                                out.println("</p></body></html>");
                        }
                                
                       return;
                    }
                    return;*/
                }
                catch(Exception e)
                {
                    out.println("<html><body><p>error"+e.toString());
                    e.printStackTrace(out);
                    out.println("</p></body></html>");
                    return;
                }
         /*   out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet KeyReceiver</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet KeyReceiver at " + request.getContextPath() + "</h1>");
            out.println("<h2>keyid="+aa+" </h2>");
            out.println("</body>");
            out.println("</html>");*/
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
