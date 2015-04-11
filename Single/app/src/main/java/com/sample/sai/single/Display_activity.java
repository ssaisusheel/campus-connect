package com.sample.sai.single;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sai on 14-02-2015.
 */
public class Display_activity extends Activity {
    public static final String TAG = "HELLO SUSHEEL";
    String gh="",f="",abc="";ArrayList list = new ArrayList();
    ArrayList<String> list2 = new ArrayList<String>();int pk,pk2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        TextView a=(TextView)findViewById(R.id.iop);
        WebView bg=(WebView)findViewById(R.id.iop1);
        String ab=getIntent().getStringExtra("Variable");
        String [] parts = ab.split("\\s+");
        for(String item:parts)
        {
            try
            {
                URL url = new URL(item);
                list.add(url);
                //  gh="<a href=\"" + url + "\">"+ url + "</a>";
                f=""+url;
            }
            catch (MalformedURLException e)
            {
                gh=  gh+" "+item;
            }
        }
        int siz = list.size();
        a.setText(gh);
        a.append("\n") ;
        a.append("\n") ;
        for(pk=0;pk<=siz-1;pk++)
        {
            list2.add("<a href=\"" + list.get(pk) + "\">"+ list.get(pk) + "</a>");
            //   a.append(Html.fromHtml(list2(pk)));
        }
        int siz2 = list2.size();
        for(pk2=0;pk2<=siz2-1;pk2++)
        {
            a.append(Html.fromHtml(list2.get(pk2)));
            a.append("\n");
            a.append("\n");
        }
        Log.d(TAG, "oPENED IN ANOTHER SCREEN");

        //TextView textview = (TextView) findViewById(R.id.textView3);
        //String value = getIntent().getStringExtra("Variable");
        //textview.setText(value);
       TextView textview1 = (TextView) findViewById(R.id.textView4);
        String value1 = getIntent().getStringExtra("Variable1");
        textview1.setText(value1);
    }


}
