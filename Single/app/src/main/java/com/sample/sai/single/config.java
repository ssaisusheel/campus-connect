package com.sample.sai.single;

/**
 * Created by chimmu123 on 1/5/2015.
 */
public interface config {

    // used to share GCM regId with application server - using php app server
    static final String APP_SERVER_URL = "http://pr-server.appspot.com/student_insertion";
    //is your ip address same yaaa same
//arey hw ip changed?..its .100..always?...day before yesterday i connected to clg wifi is dat reason? dont know may be power got off for router hmm okk
    // GCM server using java
    // static final String APP_SERVER_URL =
    // "http://192.168.1.17:8080/GCM-App-Server/GCMNotification?shareRegId=1";
// arey clint lo em changes akkarleda? ldeu is this id below yours yaa
    // Google Project Numberre
    static final String GOOGLE_PROJECT_ID = "305057381686";
    static final String MESSAGE_KEY = "message";
    static final String DOMAIN="domain";
//why is this screen coming

}
//compile and run app now one sec dont press register or send to server in ap till i tell ok