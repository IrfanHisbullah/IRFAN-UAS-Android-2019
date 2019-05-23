package com.example.irfan.irfan;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.irfan.irfan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint("Registered")
public class Irfan extends AppCompatActivity {

    private TextView textHasilJSON;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irfan);

        mQueue = Volley.newRequestQueue(this);
        textHasilJSON = findViewById(R.id.textJSON);
        Button btnJSON = (Button) findViewById(R.id.btnJSON);

        btnJSON.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                uraiJSON();
            }
        });
    }

    private void uraiJSON(){
        String url = "http://localhost/array.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("array");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject array = jsonArray.getJSONObject(i);

                        String id = array.getString("id_anggota");
                        String nama = array.getString("nama_anggota");
                        String asal_daerah = array.getString("asal_daerah");
                        String kamar = array.getString("kelompok_kamar");


                        textHasilJSON.append(id +", "+ nama+", "+ asal_daerah +", "+ kamar +"\n\n");
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();

            }
        });
        mQueue.add(request);
    }
}
