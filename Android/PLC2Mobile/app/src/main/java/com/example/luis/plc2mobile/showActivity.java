package com.example.luis.plc2mobile;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class showActivity extends AppCompatActivity {
    JSONObject json;
    LinearLayout rootLayout;
    ScrollView rootScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_show);
//        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
//        rootScroll = (ScrollView) findViewById(R.id.rootScroll);
        rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        rootScroll = new ScrollView(this);


        Intent intent = getIntent();
        String jsonString = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);



        //--------------------------------String de teste---------------------------
//        String jsonString = "{ \"DBs\" : [ {\"db1\": [ {\"contA\":\"100\"}, {\"contB\":\"500\"}, {\"result\":\"0\"}, {\"signal\":\"1\"} ] }," +
//                " {\"db2\": [ {\"contAB\":\"200\"}, {\"contBB\":\"1000\"}, {\"resultB\":\"1\"}, {\"signalB\":\"0\"} ] } ," +
//                "{\"db3\": [ {\"contAB\":\"200\"}, {\"contBB\":\"1000\"}, {\"resultB\":\"1\"}, {\"signalB\":\"0\"} ] } ," +
//                "{\"db4\": [ {\"contAB\":\"200\"}, {\"contBB\":\"1000\"}, {\"resultB\":\"1\"}, {\"signalB\":\"0\"} ] } , " +
//                "{\"db5\": [ {\"contAB\":\"200\"}, {\"contBB\":\"1000\"}, {\"resultB\":\"1\"}, {\"signalB\":\"0\"} ] } , " +
//                "{\"db6\": [ {\"contAB\":\"200\"}, {\"contBB\":\"1000\"}, {\"resultB\":\"1\"}, {\"signalB\":\"0\"} ] } ," +
//                "{\"db7\": [ {\"contAB\":\"200\"}, {\"contBB\":\"1000\"}, {\"resultB\":\"1\"}, {\"signalB\":\"0\"} ] } ," +
//                "{\"db8\": [ {\"contAB\":\"200\"}, {\"contBB\":\"1000\"}, {\"resultB\":\"1\"}, {\"signalB\":\"0\"} ] } ," +
//                "{\"db9\": [ {\"contAB\":\"200\"}, {\"contBB\":\"1000\"}, {\"resultB\":\"1\"}, {\"signalB\":\"0\"} ] } ] }";
        //--------------------------------------------------------------------------

        Log.d("json", "Merdaa: " + jsonString);
        try{
            json = new JSONObject(jsonString);
            JSONArray arrayDBs = (JSONArray) json.get("DBs");
            iterareJSON(arrayDBs);
        } catch (JSONException e){
            Toast.makeText(getApplicationContext(), "Erro no JSON! " + e.toString(), Toast.LENGTH_LONG).show();
            this.finish();
        }

        rootScroll.addView(rootLayout);
        setContentView(rootScroll);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show, menu);
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


    public void iterareJSON(JSONArray json){
        JSONObject db;
        JSONObject var;

        try{
            for(int i = 0; i < json.length(); i++){
                db = json.getJSONObject(i);
                Iterator<String> iterDB = db.keys();
                while (iterDB.hasNext()){

                    LinearLayout llDB = new LinearLayout(getApplicationContext());
                    llDB.setOrientation(LinearLayout.VERTICAL);
                    //llDB.setGravity(Gravity.CENTER_HORIZONTAL);

                    String nameDB = iterDB.next();

                    TextView txtDB = new TextView(getApplicationContext());
                    txtDB.setText(nameDB);
                    txtDB.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
                    txtDB.setTextColor(Color.BLACK);
                    txtDB.setGravity(Gravity.CENTER_HORIZONTAL);
                    txtDB.setBackgroundColor(Color.GRAY);
                    llDB.addView(txtDB);

                    //----------------------log
                    //Log.d("json", "Nome do data block: " + nameDB);
                    //-------------------------

                    JSONArray arrayVars = (JSONArray) db.get(nameDB);
                    for (int j = 0; j < arrayVars.length(); j++){

                        var = arrayVars.getJSONObject(j);
                        Iterator<String> iterVar = var.keys();
                        while (iterVar.hasNext()){

                            LinearLayout llVar = new LinearLayout(getApplicationContext());
                            llVar.setOrientation(LinearLayout.HORIZONTAL);
                            llVar.setPadding(0, 10, 0, 10);

                            String nameVar = iterVar.next();

                            TextView txtVar = new TextView(getApplicationContext());
                            txtVar.setText(nameVar);
                            txtVar.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
                            txtVar.setTextColor(Color.BLACK);
                            txtVar.setPadding(10, 0, 10, 0);

                            GradientDrawable gd = new GradientDrawable();
                            gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
                            gd.setCornerRadius(5);
                            gd.setStroke(1, 0xFF000000);
                            //txtVar.setBackgroundDrawable(gd);
                            txtVar.setBackground(gd);

                            llVar.addView(txtVar);

                            //----------------------log
                            Log.d("json", "Nome da variavel: " + nameVar);
                            //-------------------------

                            String valueVar = (String) var.get(nameVar);

                            TextView txtValue = new TextView(getApplicationContext());
                            txtValue.setText(valueVar);
                            txtValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
                            txtValue.setTextColor(Color.BLACK);
                            txtValue.setPadding(10, 0, 10, 0);

                            GradientDrawable gd1 = new GradientDrawable();
                            gd1.setColor(0xFFFFFF00); // Changes this drawbale to use a single color instead of a gradient
                            gd1.setCornerRadius(5);
                            gd1.setStroke(1, 0xFF000000);
                            //txtVar.setBackgroundDrawable(gd);
                            txtValue.setBackground(gd1);

                            llVar.addView(txtValue);

                            //----------------------log
                            Log.d("json", "Valor da variavel: " + valueVar);
                            //-------------------------
                            llDB.addView(llVar);

                        }
                    }
                    rootLayout.addView(llDB);
                }
            }
        }catch (JSONException e){

            //----------------------log
            Log.d("json", "Problema no json: " + e.toString());
            //-------------------------

        }

    }

    public void createLayout(JSONObject json){

        Iterator<String> iter = json.keys();
        while (iter.hasNext()) {
            String key = iter.next();
            Log.d("ABC", "key " + key);
            try {
                Object value = json.get(key);
                Log.d("ABC", "value " + value.toString());
                //JSONArray obj =  new JSONArray(value.toString());
                JSONArray obj = (JSONArray) value;
                if(obj == null){
                    Log.d("ABC", "obj null");
                    return;
                }
                Log.d("ABC", "Obj " + obj.toString());


                //-----------------TESTE-------------------------------
                JSONObject obj0 = (JSONObject) obj.get(0);
                Log.d("ABC", "Obj array 0 " + obj0.toString());
                //1 elemento do array (1 datablock)
                Iterator<String> iter1 = obj0.keys();
                while (iter1.hasNext()) {
                    String key2 = iter1.next();
                    Log.d("ABC", "key2 " + key2);
                    JSONArray value1 = (JSONArray) obj0.get(key2);

                    JSONObject obj2 = (JSONObject) value1.get(0);
                    Log.d("ABC", "value1 " + obj2.toString());

                    //1 elemento do array (1 variavel)
                    Iterator<String> iter2 = obj2.keys();
                    while (iter2.hasNext()) {
                        String key3 = iter2.next();
                        Log.d("ABC", "key3 " + key3);
                        Object obj3 = obj2.get(key3);
                        Log.d("ABC", "obj3 " + obj3.toString());
                    }
                }
                //-------------------------------------------------------

            } catch (JSONException e) {
                // Something went wrong!
                Log.d("ABC", "Erro no layout!");
            }
        }

//        LinearLayout ll = new LinearLayout(this);
//        ll.setOrientation(LinearLayout.HORIZONTAL);

    }
}
