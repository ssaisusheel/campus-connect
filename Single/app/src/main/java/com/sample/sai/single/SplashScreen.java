package com.sample.sai.single;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.List;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 5000;
    String a="",b="",c="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Context cw= getApplicationContext();
                remember_db db = new remember_db(cw);
             // List<remember> list = db.getAllBooks();
               Cursor cr=db.getAllBooks();
                remember book;
                book = new remember();
                if (cr.moveToFirst()) {
                    do {
                        a = cr.getString(0);
                        b = cr.getString(1);
                        c = cr.getString(2);
                    }while (cr.moveToNext());
                }


              //  remember book;
                //book =list.get(0);


                Log.d("hello susheelll", a);
                Log.d("hello susheelll", b);
                Log.d("hello susheelll", c);
                if(c.equals("yes"))
                {
                    Intent intent = new Intent(SplashScreen.this,Working.class);

                    startActivity(intent);
                }
                else {
                    // list.getAllBooks(0)
                    //    String kkk=(String)list.get(0);


                    Intent i = new Intent(SplashScreen.this, Login.class);

                    startActivity(i);


                    // close this activity
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }

}
