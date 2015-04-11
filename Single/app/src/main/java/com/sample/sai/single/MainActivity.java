package com.sample.sai.single;


//package com.example.chimmu123.client;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
//import android.app.Activity;
        import android.content.Context;
        import android.os.AsyncTask;
//import android.os.Bundle;
        import android.util.Log;
        import android.widget.Toast;


public class MainActivity extends Activity {
    ShareExternalServer appUtil;
    String regId;
    AsyncTask shareRegidTask;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String name=getIntent().getStringExtra("name");
        String id=getIntent().getStringExtra("sid");
        String password=getIntent().getStringExtra("pass");
        String classs=getIntent().getStringExtra("class");
        String year=getIntent().getStringExtra("year");
        appUtil = new ShareExternalServer(name,id,password,classs,year);


        regId = getIntent().getStringExtra("regId");
        Log.d("MainActivity", "regId: " + regId);
        dialog = ProgressDialog.show(MainActivity.this, "",
                "Uploading details to cloud. Please wait...", true);
        final Context context = this;
        new AsyncTask<Void,Void,String>() {
            @Override
            protected String doInBackground(Void... params) {

                if(regId!=null) {
                    String result = appUtil.shareRegIdWithAppServer(context, regId);
                    return result;
                }
                else
                    return "";//sorted problem // run nd check
            }

            @Override
            protected void onPostExecute(String result) {
                shareRegidTask = null;

                String gh=result.substring(0,3);
                dialog.dismiss();
                if(gh.equals("Reg"))
                {
                    Intent intent = new Intent(context,Login.class);
                    startActivity(intent);
                }
                else {

                    Toast.makeText(getApplicationContext(), result,
                            Toast.LENGTH_LONG).show();
                }




            }


        }.execute(null,null,null);

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

