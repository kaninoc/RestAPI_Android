package com.example.restapi_android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restapi_android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FilteredCustomList extends AppCompatActivity {

    TextView jsonTextView;

    private String apiKey = "9fgs56znfqc7ax8lb8gc7v2bl"; // Reemplaza con tu propia API key
    private String url = "https://www.datos.gov.co/resource/hk5x-635y.json?$where=";

    private static String column,value = "";

    private JSONArray jsonArray = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_custom_list);

        // Recuperar el extra
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("COLUMN")) {
            column = intent.getStringExtra("COLUMN");

            if (column.equals("Colegio")){
                column = "institucion_educativa";
            }else if(column.equals("Year 2020")){
                column = "a_o_2020";
            }
            url+=column+"='";
        }

        if (intent != null && intent.hasExtra("VALUE")) {
            value = intent.getStringExtra("VALUE");
            url+=value+"'";
        }

        jsonTextView = findViewById(R.id.jsonTextView);
        listFiltered(jsonTextView);
    }

    private void listFiltered(final TextView jsonTextView) {
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    jsonArray = response;

                    // Itera sobre el array y agrega la información al TextView
                    StringBuilder listaCompleta = new StringBuilder();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String municipio = jsonObject.getString("municipio");
                        String institucionEducativa = jsonObject.getString("institucion_educativa");
                        String a_o_2014 = jsonObject.getString("a_o_2014");
                        String a_o_2015 = jsonObject.getString("a_o_2015");
                        String a_o_2016 = jsonObject.getString("a_o_2016");
                        String _2017 = jsonObject.getString("_2017");
                        String _2018 = jsonObject.getString("_2018");
                        String _2019 = jsonObject.getString("_2019");
                        String a_o_2020 = jsonObject.getString("a_o_2020");

                        listaCompleta.append("MUNICIPIO: ").append(municipio).append("\n")
                                .append("COLEGIO: ").append(institucionEducativa).append("\n")
                                .append("A_O_2014: ").append(a_o_2014).append("\n")
                                .append("A_O_2015: ").append(a_o_2015).append("\n")
                                .append("A_O_2016: ").append(a_o_2016).append("\n")
                                .append("A_O_2017: ").append(_2017).append("\n")
                                .append("A_O_2018: ").append(_2018).append("\n")
                                .append("A_O_2019: ").append(_2019).append("\n")
                                .append("A_O_2020: ").append(a_o_2020).append("\n \n");

                        // Puedes agregar más campos según sea necesario
                    }

                    // Muestra la lista en el TextView
                    jsonTextView.setText(listaCompleta.toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        });

        Volley.newRequestQueue(this).add(getRequest);
    }
}