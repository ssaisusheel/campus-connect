package com.example.testing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.io.*;
//import java.math.BigDecimal;

import javax.json.*;
import javax.json.spi.*;
import javax.json.stream.*;
import javax.net.ssl.*;

import java.util.*;
import java.sql.*;
//Cannot establish a connection to jdbc:derby://localhost:1527/my_keys using org.apache.derby.jdbc.EmbeddedDriver (Unable to find a suitable driver)


public class MyGCMSender 
{
    public static PrintWriter out;
    private static String API_KEY;
    private static String URL_GCM="https://android.googleapis.com/gcm/send";
    
    public static void setKEY(String key){API_KEY=key;}
    public static String sendMessage(Map<String,String> msg,String[] recs)throws Exception
    {
        try
        {
            URL u=new URL(URL_GCM);
//            URLConnection uu=u.openConnection();
            HttpURLConnection con=(HttpURLConnection)u.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/json");
            con.setRequestProperty("Authorization","key="+API_KEY);
           // out.println("<p><b> KEY: "+API_KEY+"</b></p>");
            con.setDoInput(true);
            con.setDoOutput(true);
            OutputStream os=con.getOutputStream();
            JsonObjectBuilder builder=Json.createObjectBuilder();
            //out.println("<p>");
            for(Map.Entry<String,String> entry:msg.entrySet())
            {//here is the problem
                builder.add(entry.getKey(), entry.getValue());                
              //  out.println("</br>"+entry.getKey()+"---"+entry.getValue()+"</br>");
            }
            
            JsonArrayBuilder ab=Json.createArrayBuilder();
            //out.println("</p><p>");
            for(String idss : recs)
            {//here too they should add the id in the square bracket of json objec
                ab.add(idss);
                
                
              //  out.println("</br>"+idss+"</br>");
            }
            JsonObjectBuilder buil=Json.createObjectBuilder();
            buil.add("data",builder.build());
            buil.add("registration_ids", ab.build());
          //  out.println("<p></br>"+ab.build().toString()+"</br></p>");
            String y=buil.build().toString();
            byte[] abytes=y.getBytes();
            for(byte aty:abytes)
            {
                os.write(aty);
            }
          //  out.println("<p>sent</br>"+buil.build().toString()+"</br></p>");
            os.close();
            int resp=con.getResponseCode();
          // out.println("<p>RESP CODE"+resp+"</p></br>");
           
            
           BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
            String yy="";
            StringBuffer  buff=new StringBuffer();
            while((yy=br.readLine())!=null)
                buff.append(yy+"\n");
            String jk=buff.toString();
            jk.replace("\n","</br>");
            System.out.println("<p>RESPONSE MESSAGE</br>"+jk+"</br></p>");
            System.out.println("gcmsender's send message");
            return buff.toString();
            
            //return "ASD";
            
        }
        catch(Exception e)
        {
            out.println("<p>"+e.toString()+"</br></br></br>");e.printStackTrace(out);out.println("</p>");
            throw e;
        }        
    }        
}