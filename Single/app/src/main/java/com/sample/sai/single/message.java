package com.sample.sai.single;

import java.util.Date;
//import java.sql.Date;

/**
 * Created by chimmu123 on 3/17/2015.
 */
public class message {
    private int id;
    private String title;
    private String d;
    private Date dtt;
   // private String author;

    public message(){}

    public message(String title, String d, java.sql.Date dtt) {
        super();
        this.title = title;
        this.d=d;
       this.dtt=dtt;
      //  this.author = author;
    }
    public void setId(int i)
    {
        id=i;
    }
    public void setTitle(String g)
    {
        title=g;
    }
    public void setDate(String df)
    {
        d=df;
    }
   public void setTime(java.sql.Date dk)
  {
        dtt=dk;
    }

    //getters & setters

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title +  ",timestamp=" + d+" ]";
    }
}
