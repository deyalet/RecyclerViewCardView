package com.example.recyclerviewconcardviewitems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MostrarDatos extends AppCompatActivity {
    RecyclerView recyclerView;
    List <Revistas> revistas;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);




        extractRevistas();


    }

    private void extractRevistas(){

        Bundle bundle = this.getIntent().getExtras();
        String URL= "https://revistas.uteq.edu.ec/ws/issues.php?j_id="+ bundle.getString("COD");


        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                revistas = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                        JSONObject revistaObject = response.getJSONObject(i);
                        revistas.add(new Revistas(revistaObject.getString("issue_id"), revistaObject.getString("volume"),
                                revistaObject.getString("number"), revistaObject.getString("year"),
                                revistaObject.getString("date_published"), revistaObject.getString("title"),
                                revistaObject.getString("doi"), revistaObject.getString("cover")));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                adapter = new Adapter(getApplicationContext(),revistas);
                recyclerView = findViewById(R.id.RevistaList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse: "+ error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }
}