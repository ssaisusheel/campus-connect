package com.example.testing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class Upload extends HttpServlet {
    /**
	 * 
	 */
	//private static final long serialVersionUID = 5044572858341804788L;
	/**
	 * 
	 */
	//private static final long serialVersionUID = 5044572858341804788L;
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	String abc="";String ykp="",a1="",a2="";
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
    	//PrintWriter pw=res.getWriter(); 
    	PrintWriter out=res.getWriter();
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("myFile");
        BlobKey blobkey ;
        String s=req.getParameter("pattern");
         ykp=req.getParameter("dm");
      //  req.setAttribute("domn",ykp);
        a1=req.getParameter("id");
        //req.setAttribute("id",a1);
         a2=req.getParameter("cd");
         String you=req.getParameter("sss");
         out.println(""+ykp);
        // out.println(""+ykp);
    if(you.equals("SEND"))
         {
        	 abc=s;
        	 req.setAttribute("myname",abc);
           	 req.setAttribute("domn",ykp);
        	 req.setAttribute("id",a1);
        	 req.setAttribute("cd",a2);
        	 out.println(""+ykp);
        	 RequestDispatcher rd1=req.getRequestDispatcher("/sender");  
             try{
         	rd1.forward(req, res);  
         		}
         		catch(Exception e)
         		{
         			out.println(e);	
         		}
        	 
         }
         
         //if()
        //req.setAttribute("cd",a2);
       // final BlobInfo blobInfo = new BlobInfoFactory().loadBlobInfo(blobKey);
     //   long size = blobInfo.getSize();
        //	String s=req.getParameter("pattern");
         else
         {
        	 
         
        if (blobKeys == null || blobKeys.isEmpty())
        {
           // res.sendRedirect("/");
            abc=s;
       	 req.setAttribute("myname",abc);
       	 req.setAttribute("domn",ykp);
    	 req.setAttribute("id",a1);
    	 req.setAttribute("cd",a2);
            RequestDispatcher rd1=req.getRequestDispatcher("/sender");  
             try{
         	rd1.forward(req, res);  
         		}
         		catch(Exception e)
         		{
         			out.println(e);	
         		}
        }
        else 
        {
        	blobkey=blobKeys.get(0);
        	 final BlobInfo blobInfo = new BlobInfoFactory().loadBlobInfo(blobkey);
        	 
        	 long size = blobInfo.getSize();
        	 if(size > 0)
        	 {
        		  //process blob
        		// res.sendRedirect("/serve?blob-key=" + blobKeys.get(0).getKeyString());
        		 abc=s+"\n"+"http://pr-server.appspot.com/serve?blob-key=" +blobKeys.get(0).getKeyString();
            	 req.setAttribute("myname",abc);
            	// out.println(ykp);
            	 req.setAttribute("domn",ykp);
            	 req.setAttribute("id",a1);
            	 req.setAttribute("cd",a2);
            	 //out.println(""+getAttribute())
            	// out.println(""+ykp);
            	// a2=req.getParameter("cd");
            	// a1=req.getParameter("id");
            	//out.println( " "+req.getParameter("uuu")+""+" "+" "+req.getParameter("sss"));
             RequestDispatcher rd1=req.getRequestDispatcher("/message.jsp");  
                  try{
              	rd1.forward(req, res);  
              		}
              		catch(Exception e)
              		{
              		 out.println(e);	
              		}
        		}
        	 	else{
        	 		blobstoreService.delete(blobkey);
        		//  res.sendRedirect("/");
        	 			abc=s;
        	       	 req.setAttribute("myname",abc);
        	       	 req.setAttribute("domn",ykp);
                	 req.setAttribute("id",a1);
                	 req.setAttribute("cd",a2);
                	// out.println( " "+req.getParameter("uuu")+""+" "+" "+req.getParameter("sss"));
        	            RequestDispatcher rd1=req.getRequestDispatcher("/sender");  
        	             try{
        	         	rd1.forward(req, res);  
        	         		}
        	         		catch(Exception e)
        	         		{
        	         	       out.println(e);	
        	         		}
        		}
        }
    }
       // else {
           
        	/*abc=s+"\n"+"http://check-s.appspot.com/serve?blob-key=" +blobKeys.get(0).getKeyString();
        	 req.setAttribute("myname",abc);
             RequestDispatcher rd1=req.getRequestDispatcher("/index.jsp");  
              try{
          	rd1.forward(req, res);  
          		}
          		catch(Exception e)
          		{
          		System.out.println(e);	
          		}
             // res.sendRedirect("/index.jsp");
        	//pw.println(abc);*/
       	
        }
    }

