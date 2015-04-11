package com.sample.sai.single;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class RegisterActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener{

    Button btnGCMRegister;
    Button btnAppShare;
    GoogleCloudMessaging gcm;
    Context context;
    String regId;
    Toolbar toolbar;
    Spinner spinner,spinner1;
    EditText nametext,studentid,passtext;
    Button register,share;
    TextView mytext;
    String Name;
    String Password;
    String EditPassword;
    String branch;
    String year;

    public static final String REG_ID = "regId";
    private static final String APP_VERSION = "appVersion";

    static final String TAG = "Register Activity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.tool_bar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter adapter= ArrayAdapter.createFromResource(this,R.array.dept,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter adapter1= ArrayAdapter.createFromResource(this,R.array.years,android.R.layout.simple_spinner_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);
        nametext=(EditText)findViewById(R.id.name);
        studentid=(EditText)findViewById(R.id.pwdid);
        passtext=(EditText)findViewById(R.id.editpassword);

        btnGCMRegister = (Button) findViewById(R.id.regbutton);
        btnGCMRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final String Name=nametext.getText().toString();
                final String Password=studentid.getText().toString();
                final String EditPassword=passtext.getText().toString();
                if(Name.length()==0)
                {
                    nametext.requestFocus();
                    nametext.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!Name.matches("[a-zA-Z ]+"))
                {
                    nametext.requestFocus();
                    nametext.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(Password.length()==0)
                {
                    studentid.requestFocus();
                    studentid.setError("FIELD CANNOT BE EMPTY");
                }
                else if(EditPassword.length()==0)
                {
                    passtext.requestFocus();
                    passtext.setError("FIELD CANNOT BE EMPTY");
                }
                else if(spinner.getSelectedItemPosition()==0){
                    Toast.makeText(RegisterActivity.this, "Please select Branch ",Toast.LENGTH_LONG).show();

                }
                else if(spinner1.getSelectedItemPosition()==0){
                    Toast.makeText(RegisterActivity.this, "Please select Year ",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Validation Successful" ,Toast.LENGTH_LONG).show();
                    if (TextUtils.isEmpty(regId)) {
                        regId = registerGCM();
                        Log.d("RegisterActivity", "GCM RegId: " + regId);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Already Registered with GCM Server!",
                                Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

        btnAppShare = (Button) findViewById(R.id.share);
        btnAppShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                final String Name=nametext.getText().toString();
                final String Password=studentid.getText().toString();
                final String EditPassword=passtext.getText().toString();
                if(Name.length()==0)
                {
                    nametext.requestFocus();
                    nametext.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!Name.matches("[a-zA-Z ]+"))
                {
                    nametext.requestFocus();
                    nametext.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(Password.length()==0)
                {
                    studentid.requestFocus();
                    studentid.setError("FIELD CANNOT BE EMPTY");
                }
                else if(EditPassword.length()==0)
                {
                    passtext.requestFocus();
                    passtext.setError("FIELD CANNOT BE EMPTY");
                }
                else if(spinner.getSelectedItemPosition()==0){
                    Toast.makeText(RegisterActivity.this, "Please select Branch ",Toast.LENGTH_LONG).show();

                }
                else if(spinner1.getSelectedItemPosition()==0){
                    Toast.makeText(RegisterActivity.this, "Please select Year ",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(RegisterActivity.this, "Validation Successful", Toast.LENGTH_LONG).show();

                    branch= spinner.getSelectedItem().toString();
                    year=spinner1.getSelectedItem().toString();
                    if (TextUtils.isEmpty(regId)) {
                        Toast.makeText(getApplicationContext(), "RegId is empty!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Intent i = new Intent(getApplicationContext(),
                                MainActivity.class);
                        i.putExtra("regId", regId);
                        i.putExtra("name", Name);
                        i.putExtra("sid", Password);
                        i.putExtra("pass", EditPassword);
                        i.putExtra("class", branch);
                        i.putExtra("year", year);
                        Log.d("RegisterActivity",
                                "onClick of Share: Before starting main activity.");
                        startActivity(i);
                        finish();
                        Log.d("RegisterActivity", "onClick of Share: After finish.");
                    }
                }

            }
        });
    }

    public String registerGCM() {

        gcm = GoogleCloudMessaging.getInstance(this);
        regId = getRegistrationId(context);

        if (TextUtils.isEmpty(regId)) {

            registerInBackground();

            Log.d("RegisterActivity",
                    "registerGCM - successfully registered with GCM server - regId: "
                            + regId);
        } else {
            Toast.makeText(getApplicationContext(),
                    "RegId already available. RegId: " + regId,
                    Toast.LENGTH_LONG).show();
        }
        return regId;
    }

    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getSharedPreferences(
                MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        String registrationId = prefs.getString(REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        int registeredVersion = prefs.getInt(APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            Log.d("RegisterActivity",
                    "I never expected this! Going down, going down!" + e);
            throw new RuntimeException(e);
        }
    }

    private void registerInBackground() {
        new AsyncTask<Void,Void,String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regId = gcm.register(config.GOOGLE_PROJECT_ID);
                    Log.d("RegisterActivity", "registerInBackground - regId: "
                            + regId);
                    msg = "Device registered, registration ID=" + regId;

                    storeRegistrationId(context, regId);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    Log.d("RegisterActivity", "Error: " + msg);
                }
                Log.d("RegisterActivity", "AsyncTask completed: " + msg);
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                Toast.makeText(getApplicationContext(),
                        "Registered with GCM Server." + msg, Toast.LENGTH_LONG)
                        .show();
            }
        }.execute(null, null, null);
    }

    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getSharedPreferences(
                MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, regId);
        editor.putInt(APP_VERSION, appVersion);
        editor.commit();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner2 = (Spinner)parent;
        if(spinner2.getId() == R.id.spinner) {

            mytext = (TextView) view;
            //Toast.makeText(this, "you belong to" + mytext.getText(), Toast.LENGTH_SHORT).show();
        }
        else if(spinner2.getId() == R.id.spinner1) {
            TextView mytext1 = (TextView) view;
            // Toast.makeText(this, "this is year " + mytext1.getText(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}