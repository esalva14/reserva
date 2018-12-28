package com.example.reservapp;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.String;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //mTextView = (TextView) findViewById(R.id.text_message);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setHomeButtonEnabled(false);
        }*/
    }
    /*
        @Override
        public void onDestroy() {
            super.onDestroy();
            android.os.Debug.stopMethodTracing();
        }

        @Override
        public void onPause() {
            super.onPause();
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        protected void onStop() {
            super.onStop();

            ContentValues values = new ContentValues();
            values.put(NotePad.Notes.COLUMN_NAME_NOTE, getCurrentNoteText());
            values.put(NotePad.Notes.COLUMN_NAME_TITLE, getCurrentNoteTitle());

            getContentResolver().update(
                    mUri,    // The URI for the note to update.
                    values,  // The map of column names and new values to apply to them.
                    null,    // No SELECT criteria are used.
                    null     // No WHERE columns are used.
            )
        }

        @Override
        protected void onStart() {
            super.onStart();

            LocationManager locationManager =
                    (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (!gpsEnabled) {
                // Create a dialog here that requests the user to enable GPS, and use an intent
                // with the android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS action
                // to take the user to the Settings screen to enable GPS when they click "OK"
            }
        }

        @Override
        protected void onRestart() {
            super.onRestart();
        }
    */
    public void Entrar() {
        Intent intent = new Intent(this, Usuari.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String contra = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, contra);

        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String telf = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE2, telf);

        startActivity(intent);
    }

    public void connectServer(View v) {

        System.out.println("connectServer()");

        EditText telf_aux = (EditText) findViewById(R.id.editText);
        String telf = telf_aux.getText().toString();

        EditText pswd_aux = (EditText) findViewById(R.id.editText2);
        String pswd = pswd_aux.getText().toString();

        String url="http://192.168.1.36:9000/message?telf="+telf+"&pswd="+pswd;

        //Aqui s'ha de posar la Ip de la xarxa local on estigui el Play.
        new login(this).execute(url);

    }

    private class login extends AsyncTask<String, Void, String> {

        InputStream stream = null;
        String str = "";
        String result = null;

        Context context;
        private login(Context context){
            this.context=context;
        }

        protected String doInBackground(String... urls) {
            try {

                System.out.println("doInBackground()");
                String query = String.format(urls[0]);
                URL url = new URL(query);
                System.out.println(url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                stream = conn.getInputStream();

                BufferedReader reader = null;

                StringBuilder sb = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(stream));

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();
                System.out.println("Result ->");
                System.out.println(result);

                Log.i("login", result);


                return result;

            } catch (IOException e) {
                return null;
            }
        }

        protected void onPostExecute(String result) {
            //EditText n = (EditText) findViewById(R.id.editText);
            //n.setText(result);
            System.out.println("onPostExecute()");
            System.out.println(result);

            if(result.contains("OK")) {
                System.out.println("Inside");
                Entrar();

            }
        }
    }
}