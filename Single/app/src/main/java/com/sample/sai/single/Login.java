package com.sample.sai.single;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Login extends ActionBarActivity {
    Toolbar toolbar;
    Button button,button1;
    EditText idd;
    EditText pd;
    CheckBox cc;
    String sid="";
    String body=null;
    String pas="";
    String result="";
    String yes="yes";
    String no="no";
    ProgressDialog dialog;
    AsyncTask shareRegidTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        idd=(EditText)findViewById(R.id.l1).findViewById(R.id.id1);
        pd=(EditText)findViewById(R.id.l2).findViewById(R.id.pwd2);
       cc=(CheckBox) findViewById(R.id.l2check).findViewById(R.id.chkRememberPassword);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        addListenerOnButton();

    }
    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.signup);
        button1= (Button)findViewById(R.id.login);
        button1.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View arg0) {
                                          /* Intent intent = new Intent(context, Working.class);

                                           startActivity(intent);*/


                                            dialog = ProgressDialog.show(Login.this, "",
                                                   "Verifying details. Please wait...", true);
               final String sidd=idd.getText().toString();
                final String pass=pd.getText().toString();

                Log.d("l", "" + sidd);
                new AsyncTask<Void,Void,String>() {
                    @Override
                    protected String doInBackground(Void... params)
                    {
                        URL serverUrl = null;
                        //  String body=null;
                        //String result = "";

                        try {
                            try {
                                serverUrl = new URL(config2.APP_SERVER_URL);

                                // serverUrl2 = new URL(config2.APP_SERVER_URL);
                            } catch (MalformedURLException e) {
                                Log.e("AppUtil", "URL Connection Error: "
                                        + config.APP_SERVER_URL, e);
                                result = "Invalid URL: " + config2.APP_SERVER_URL;
                            }
                            StringBuilder postBody = new StringBuilder();
                            postBody.append("id="+sidd);
                            postBody.append("&password="+pass);
                            body = postBody.toString();
                            Log.d("sai sushheeel", "" + body);
                            byte[] bytes = body.getBytes();
                            HttpURLConnection httpCon = null;
                            try {
                                httpCon = (HttpURLConnection) serverUrl.openConnection();
                                httpCon.setDoOutput(true);

                                httpCon.setUseCaches(false);
                                httpCon.setFixedLengthStreamingMode(bytes.length);
                                httpCon.setRequestMethod("POST");
                                //httpCon.setRequestProperty("Content-Type",
                                //      "application/x-www-form-urlencoded;charset=UTF-8");
                                OutputStream out = httpCon.getOutputStream();

                                out.write(bytes);
                                out.close();
                                // httpCon.setDoInput(true);
                                int status = httpCon.getResponseCode();
                                BufferedReader bf=new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
                                String gh=bf.readLine();

                                //    String gh= httpCon.getResponseMessage();
                                //String gh=httpCon.getHeaderField("login-checking");
                                Log.d("sai", "" + gh);
                                if (status == 200 && gh!=null) {
                                    if (gh.equals("verified"))
                                    {
                                        result ="true";
                                    }
                                    else {
                                        Log.d("checkvalid","not valid");
                                        result = "Enter valid details";
                                       // idd.setText("");

                                        //pd.setText("");
                                    }


                                } else {
                                    result = "Post Failure." + " Status: " + status;
                                }
                            } finally {
                                if (httpCon != null) {
                                    httpCon.disconnect();
                                }
                            }
                        }
                        catch (IOException e) {
                            // System.out.println(httpCon);
                            result = "Post Failure11111. Error in sharing with App Server."+e.toString();
                            //k=k+1;


                            Log.e("AppUtil", "Error in sharing with App Server: " + e + "--" + Log.getStackTraceString(e));

                        }
                        return result;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                       /* shareRegidTask = null;
                        Toast.makeText(getApplicationContext(), result,
                                Toast.LENGTH_LONG).show();*/
                        dialog.dismiss();
                       if(result.equals("true"))
                        {
                            Context cw= getApplicationContext();
                            if(cc.isChecked())
                            {
                                remember_db db = new remember_db(cw);

                                db.insertMsg(sidd,pass,"yes");
                            }
                            else
                            {
                                remember_db db = new remember_db(cw);

                                db.insertMsg(sidd,pass,"no");
                            }
                            Intent intent = new Intent(context,Working.class);

                            startActivity(intent);
                        }
                        else if(result.equals("Enter valid details")) {
                            Toast.makeText(getApplicationContext(), result,
                                    Toast.LENGTH_LONG).show();
                            idd.setText("");

                            pd.setText("");
                        }

                    }


                }.execute(null,null,null);


            }

        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context,RegisterActivity.class);
                startActivity(intent);

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
