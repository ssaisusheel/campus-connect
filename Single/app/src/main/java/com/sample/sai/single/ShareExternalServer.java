package com.sample.sai.single;

/**
 * Created by chimmu123 on 1/5/2015.
 */

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ShareExternalServer {
    String a1="";
    String b1="";
    String c1="";
    String d1="";
    String e1="";
    public ShareExternalServer(String a,String b,String c,String d,String e)
    {
        a1=a;
        b1=b;
        c1=c;
        d1=d;
        e1=e;
    }

    public String shareRegIdWithAppServer(final Context context,
                                          final String regId) {

        String result = "";
        int k=0;String body=null;
        Map paramsMap = new HashMap();
        URL serverUrl2 = null;
        paramsMap.put("regId", regId);
        try {
            URL serverUrl = null;
            try {
                serverUrl = new URL(config.APP_SERVER_URL);
            } catch (MalformedURLException e) {
                Log.e("AppUtil", "URL Connection Error: "
                        + config.APP_SERVER_URL, e);
                result = "Invalid URL: " + config.APP_SERVER_URL;
            }
            try {
                // serverUrl = new URL(config.APP_SERVER_URL);
                serverUrl2 = new URL(config2.APP_SERVER_URL);
            } catch (MalformedURLException e) {
                Log.e("AppUtil", "URL Connection Error: "
                        + config2.APP_SERVER_URL, e);
                result = "Invalid URL: " + config2.APP_SERVER_URL;
            }

            StringBuilder postBody = new StringBuilder();
            Iterator iterator = paramsMap.entrySet()
                    .iterator();

            while (iterator.hasNext()) {
                Entry param = (Entry) iterator.next();
                postBody.append(param.getKey()).append('=')
                        .append(param.getValue());
                if (iterator.hasNext()) {
                    postBody.append('&');
                }
            }
            postBody.append("&name="+a1);
            postBody.append("&id="+b1);
            postBody.append("&password="+c1);
            postBody.append("&class="+d1);
            postBody.append("&year="+e1);

            body = postBody.toString();
            Log.d("sai sushheeel", ""+body);
            byte[] bytes = body.getBytes();
            HttpURLConnection httpCon = null;
            try {
                httpCon = (HttpURLConnection) serverUrl.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setUseCaches(false);
                httpCon.setFixedLengthStreamingMode(bytes.length);
                httpCon.setRequestMethod("POST");
                httpCon.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                OutputStream out = httpCon.getOutputStream();
                //servlet problemm ra surely  will u see dat once?yes i noticed that it has a package name but you didnt make the pac
                // kage structure hw to make it?
                //dont make it remove that name from java file and then make the compilation u mean in GCMNotification ah?yes
//shall i run it?
                out.write(bytes);
                out.close();

                int status = httpCon.getResponseCode();
                if (status == 200){
                    result = "RegId shared with Application Server. RegId: "
                            + regId;
                } else {
                    result = "Post Failure." + " Status: " + status;
                }
            } finally {
                if (httpCon != null) {
                    httpCon.disconnect();
                }
            }

        } catch (IOException e) {

            result = "Post Failure11111. Error in sharing with App Server."+e.toString();k=k+1;
            Log.e("AppUtil", "Error in sharing with App Server: " + e+"--"+Log.getStackTraceString(e));

        }
        if(k!=0)
        {
            HttpURLConnection httpCon2 = null;byte[] bytes2 = body.getBytes();
            try {
                httpCon2 = (HttpURLConnection) serverUrl2.openConnection();
                httpCon2.setDoOutput(true);
                httpCon2.setUseCaches(false);
                httpCon2.setFixedLengthStreamingMode(bytes2.length);
                httpCon2.setRequestMethod("POST");
                httpCon2.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                OutputStream out = httpCon2.getOutputStream();

                out.write(bytes2);
                out.close();
                int status = httpCon2.getResponseCode();
                String gh= httpCon2.getResponseMessage();
                if (status == 200) {
                    result = "RegId shared with Application Server. RegId: "
                            +gh+ regId;
                } else {
                    result = "Post Failure." + " Status: " + status;
                }
            }
            catch (IOException e1) {
                result = "Post Failure.1232121 Error in sharing with App Server."+e1.toString()+k;
                Log.e("AppUtil", "Error in sharing with App Server: " + e1+"--"+Log.getStackTraceString(e1));

            }
            finally {
                if (httpCon2 != null) {
                    httpCon2.disconnect();
                }
            }

        }

        return result;
    }
}