package com.example.testing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.json.*;
//import javax.json.spi.*;
//import javax.json.stream.*;
import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.net.ssl.*;
import javax.servlet.RequestDispatcher;



/**
 *
 * @author TOSHIBA
 */
public class sender extends HttpServlet {

    PrintWriter outt;
    String a="",domain="",idd="",cdd="";
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); {
            /* TODO output your page here. You may use following sample code. */
          /*  out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SENDER</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SENDER at " + request.getContextPath() + "</h1>");
            javax.servlet.ServletContext con=this.getServletContext();*/
             a=(String)request.getAttribute("myname");
             //domain=(String)request.getAttribute("domn");
             domain=(String)request.getSession().getAttribute("domain");
             idd=(String)request.getAttribute("id");
             cdd=(String)request.getAttribute("cd");
             
             out.println(""+domain);
            out.println("\n"+a);
             out.println("\n"+idd);
             out.println("\n"+cdd);
             
            //String subj=(String)con.getAttribute("subject");
            System.out.println("<p>sending msg ....</p></br>");
            outt=out;
            boolean aaa=sendMessage(a,domain,idd,cdd);
           if(aaa)
            {
                 RequestDispatcher rd=request.getRequestDispatcher("/success.jsp");  
		try{
		rd.forward(request, response);  
		}
		catch(Exception e)
		{
			
		}
            }
            
            //out.println("<p>"+aaa+"</p>");
            
            //outt.println("<p>"+a+"</p>");
           // out.println("</body>");
          //  out.println("</html>");
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
    private boolean sendMessage(String a,String domain,String idd,String cdd)
    {
         String aaap[]=null;
         String dom=domain;
         String ikk=idd;
         String cddd=cdd;
    //    try
       // {
           // String aaap[]=null;
            try{
                Class.forName("com.mysql.jdbc.GoogleDriver");
                Connection c=DriverManager.getConnection("jdbc:google:mysql://quantum-bonus-91507:cfua/m_project?user=root");
            Statement s=c.createStatement();
            ResultSet rs=s.executeQuery("select distinct reg_id from reg_keys where (a1='"+ikk+"' ) or (a2='"+ikk+"' ) or (a3='"+ikk+"') ");
            int aa=0;
            String array[]=new String[1024];
            while(rs.next())
            {
                array[aa++]=rs.getString("reg_id");
            }
            aaap=new String[aa];
            for(int i=0;i<aa;i++)
                aaap[i]=array[i];
       //     }
        
      //  catch(Exception e)
        //    {
               // BufferedReader rs=new BufferedReader(new InputStreamReader(new FileInputStream("C:\\regIDS.dat")));
              
         /*  BufferedReader rs=new BufferedReader(new InputStreamReader(new FileInputStream("regIDS.dat")));
                int aa=0;
                String aop="";
            String array[]=new String[1024];
            while((aop=rs.readLine())!=null)
            {
                array[aa++]=aop;
            }
            aaap=new String[aa];
            for(int i=0;i<aa;i++)
                aaap[i]=array[i];*/
         //   } 
            //YOUR PROJECT KEY GOES IN HERE
           javax.servlet.ServletContext con=this.getServletContext();
   	//if(con.getAttribute("API_KEY")==null)
		MyGCMSender.setKEY("AIzaSyDCDV5XURcj1XeNm5sCjZ3ckQBbXZz95vk");
	// else       
         //      MyGCMServerSender.setKEY((String)(con.getAttribute("API_KEY")));
                       
            MyGCMSender.out=outt;
            java.util.HashMap<String,String> map=new java.util.HashMap<String,String>();
            map.put("message",a);
            map.put("domain", dom);
            
           // map.put("SUBJECT",subj);
            //outt.println("<p></br><b>"+map+"</b></br></p>");
            String aaa=MyGCMSender.sendMessage(map, aaap);
            System.out.println("sender's send message");
        	c.close();
            //outt.println("<p></br>"+aaa+"</br></p>");
            if(aaa!=null )
                return true;
            else
                return false;
          
            
            
        
            

    
      
    }
            
        
        catch(Exception e)
        {
            //System.out.println(e);
            outt.println("<p></br><b>"+e.toString()+"</b></br></p>");
            return false;
        }
        
}
}


