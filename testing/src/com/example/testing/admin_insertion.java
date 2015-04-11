package com.example.testing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chimmu123
 */
public class admin_insertion extends HttpServlet {

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
        String sid = "project"; 
 
        String url="jdbc:oracle:thin:@"+serverName+":"+ portNumber+":"+sid; 

 
        String username = "SYS AS SYSDBA"; 
        String password = "susheel786"; */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          /*  out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet admin_insertion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet admin_insertion at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
            String name_text=request.getParameter("admin_name");
            String id_text=request.getParameter("admin_id");
            String pass_text=request.getParameter("admin_pass");
            String domain_text=request.getParameter("admin_domain");
            String gf=request.getParameter("s");
            String year_select=request.getParameter("yr");
            if(name_text.equals("")||id_text.equals("")||pass_text.equals("")||domain_text.equals("")||gf.equals("")||year_select.equals(""))
            {
                 RequestDispatcher rd=request.getRequestDispatcher("/signup.html");  
		try{
		rd.forward(request, response);  
		}
		catch(Exception e)
		{
			out.println(e);
		}
            }
            
            else
            {
              //  out.println("hello");
                //out.println(id_text);
               // out.println(name_text);
                System.out.println("hello i am here");
                 try
                 
            {   
                Class.forName("com.mysql.jdbc.GoogleDriver");
                System.out.println("hello i am here sai susheel");
                Connection c=DriverManager.getConnection("jdbc:google:mysql://pr-server:project-server/m_project?user=root");
                System.out.println("hii susheel");
                Statement s=c.createStatement();
                 System.out.println("hii susheel in cse2");
                //boolean ss=s.execute("insert into admins values('"+id_text+"','"+name_text+"','"+pass_text+"','"+domain_text+"')");                
                 boolean ss=s.execute("insert into admins values('"+id_text+"','"+name_text+"','"+pass_text+"','"+domain_text+"','"+gf+"','"+year_select+"')");
                 //s.executeUpdate(sid)
               // response.setHeader("me done",aa);
                
                System.out.println("hello!!!"+ss);
                if(!ss)
                {
                     RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
		try{
		rd.forward(request, response);  
		}
		catch(Exception e)
		{
			out.println(e);
		}
                }
                

            }
                 catch(Exception e)
                 {
                     out.println(e);
                 }
                
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
