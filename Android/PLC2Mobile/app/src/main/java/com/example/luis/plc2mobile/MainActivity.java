package com.example.luis.plc2mobile;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText edTA;
    private EditText edTB;
    private EditText edTC;
    private EditText edTD;
    public final static String EXTRA_MESSAGE = "com.example.luis.plc2mobile.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edTA = (EditText) findViewById(R.id.edTA);
        edTB = (EditText) findViewById(R.id.edTB);
        edTC = (EditText) findViewById(R.id.edTC);
        edTD = (EditText) findViewById(R.id.edTD);
        edTA.setText("192");
        edTB.setText("168");
        edTC.setText("1");
        edTD.setText("40");


        //Para testes apenas-----------------------------------------------
        //-----------------------------------------------------------------
//        Intent intent = new Intent(getApplicationContext(), showActivity.class);
//        startActivity(intent);
        //----------------------

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

    public void connectPLC(View view) {
        // Gets the URL from the UI's text field.

        String stringUrl = "http://" + edTA.getText().toString() + "." + edTB.getText().toString() + "." + edTC.getText().toString() + "." + edTD.getText().toString() + "/awp/vars.html";
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute(stringUrl);
        } else {
            Toast.makeText(getApplicationContext(), "Sem ligação a rede!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(getApplicationContext(), showActivity.class);
            //EditText editText = (EditText) findViewById(R.id.edit_message);
            //String message = editText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, result);
            startActivity(intent);

        }
    }

    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            //int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            //String contentAsString = readIt(is);
            return readIt(is);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream) throws IOException {
//        Reader reader = null;
//        reader = new InputStreamReader(stream, "UTF-8");
//        char[] buffer = new char[len];
//        reader.read(buffer);
//        return new String(buffer);

        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return total.toString();
    }


}
