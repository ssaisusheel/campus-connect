package com.sample.sai.single;

import java.util.Date;
//import java.sql.Date;

/**
 * Created by chimmu123 on 3/17/2015.
 */
public class remember {
    private String id;
    private String title;
    private String d;
    private Date dtt;
    // private String author;

    public remember(){}

    public remember(String user, String password, String status) {
        super();
        id=user;
        title=password;
        d=status;
        this.title = title;
        this.d=d;
        this.dtt=dtt;
        //  this.author = author;
    }
    public void setId(String i)
    {
        id=i;
    }
    public void setPass(String g)
    {
        title=g;
    }
    public void setstatus(String df)
    {
        d=df;
    }
    public String getId()
    {
        return id;
    }
    public String getPass()
    {
        return title;
    }
    public String getstatus()
    {
        return d;
    }
   // public void setTime(java.sql.Date dk)
   // {
     //   dtt=dk;
    //}

    //getters & setters

    @Override
    public String toString() {
        return "user [id=" + id + ", pass=" + title +  ",status=" + d+" ]";
    }
}
