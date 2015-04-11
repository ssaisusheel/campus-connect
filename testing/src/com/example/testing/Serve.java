package com.example.testing;

//package com.example.check;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import java.util.*;
//import com.google.appengine.api.datastore.*;

public class Serve extends HttpServlet {
    /**
	 * 
	 */
//	private static final long serialVersionUID = 7266182492696488616L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {
    	PrintWriter pw=res.getWriter(); 
            BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
            String uri = req.getScheme() + "://" +   // "http" + "://
                    req.getServerName() +       // "myhost"
                    ":" +                           // ":"
                    req.getServerPort() +       // "8080"
                    req.getRequestURI() +       // "/people"
                    "?" +                           // "?"
                    req.getQueryString();       // "lastname=Fox&age=30"
         //   pw.println(uri);
          //  System.out.println(uri);
        //    setParameter(uri);
            req.setAttribute("myname",uri);
          //  RequestDispatcher rd1=req.getRequestDispatcher("/index.jsp");  
            //try{
        	//rd1.forward(req, res);  
        		//}
        		//catch(Exception e)
        		//{
        		//System.out.println(e);	
        		//}
          blobstoreService.serve(blobKey, res);
          
        //    DatastoreService 
          //  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        }
}
