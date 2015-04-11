package com.sample.sai.single;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class Tab2 extends Fragment {
    Cursor cursor;
    String ft;
    SimpleAdapter mSchedule = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("tab2","gott u");

        final View v =inflater.inflate(R.layout.tab_2,container,false);
        Context cw= v.getContext();
        final ListView listContent = (ListView)v.findViewById(R.id.contentlist);
        Databasehelper1 db1 = new Databasehelper1(cw);

        // db.openToRead();
        //db.deleteAll();
        cursor = db1.queueAll();
        //startManagingCursor(cursor);
        /*String[] from = new String[]{Databasehelper.MESSAGE};
        int[] to = new int[]{R.id.text};
        SimpleCursorAdapter cursorAdapter =
                new SimpleCursorAdapter(cw, R.layout.row, cursor, from, to);

        listContent.setAdapter(cursorAdapter);*/
        Log.d("tab2","gott u yayyyyy");
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        if (cursor.moveToFirst()) {
            do {

                //Integer.parseInt(cursor.getString(0));
                map = new HashMap<String, String>();

                String msg = cursor.getString(1);
                Log.d("msgin tab1",msg);
                map.put("message", msg);
                String dated = cursor.getString(2);
                  String g=dated.substring(0,10);
                String min=dated.substring(14,16);
                int time= Integer.parseInt(dated.substring(11,13));
                int time1;
                if(time>12)
                {
                    time1= time-12;
                    ft= time1 + ":" + min +" "+ "PM";

                }
                else
                {
                    ft= dated.substring(11,16) + " "+"AM";
                }

                Log.d("datein tab1", g);
                Log.d("timein", ft);
                map.put("date", g);
                map.put("timed", ft);
                mylist.add(map);

            } while (cursor.moveToNext());
        }
        String[] from = new String[]{"message", "date", "timed"};
        int[] to = new int[]{R.id.text, R.id.text1, R.id.text2};
        mSchedule = new SimpleAdapter(cw, mylist, R.layout.row, from, to);
        listContent.setAdapter(mSchedule);
        listContent.setTextFilterEnabled(true);


        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //final String text = (String) ((TextView)view).getText();
                TextView tv = (TextView)view.findViewById(R.id.text);
                String product = tv.getText().toString();
                Log.v("mSelectedProduct", product);

                TextView tv1 = (TextView)view.findViewById(R.id.text1);
                String product1 = tv1.getText().toString();
                Log.v("mSelectedProduct1", product1);
                TextView tv2 = (TextView)view.findViewById(R.id.text2);
                String product2 = tv2.getText().toString();
                Log.v("mSelectedProduct2", product2);

                System.out.println("valuee = : " + product);
                System.out.println("valuee1 = : " + product1);
                final String date1 = product1.substring(0,2);
                int k= Integer.parseInt(product1.substring(3,5));

                final String monthString;
                switch (k)
                {
                    case 1:  monthString = "January";
                        break;
                    case 2:  monthString = "February";
                        break;
                    case 3:  monthString = "March";
                        break;
                    case 4:  monthString = "April";
                        break;
                    case 5:  monthString = "May";
                        break;
                    case 6:  monthString = "June";
                        break;
                    case 7:  monthString = "July";
                        break;
                    case 8:  monthString = "August";
                        break;
                    case 9:  monthString = "September";
                        break;
                    case 10: monthString = "October";
                        break;
                    case 11: monthString = "November";
                        break;
                    case 12: monthString = "December";
                        break;
                    default: monthString = "Invalid month";
                        break;

                }
                final String  dateformat1 = monthString + " " + date1;
                Intent intent = new Intent(v.getContext(), Display_activity2.class);
                intent.putExtra("Variable",product);
                intent.putExtra("Variable1",dateformat1);
                intent.putExtra("Variable2",product2);
                startActivity(intent);



                Toast.makeText(v.getContext(),
                        product , Toast.LENGTH_SHORT).show();

            }
        });
        Log.d("tab2","gott u yayyyyy yay ");
        return v;
    }


}
