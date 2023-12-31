package com.example.restapi_android.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restapi_android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

public class FullList extends AppCompatActivity {

    TextView jsonTextView;

    private String apiKey = "9fgs56znfqc7ax8lb8gc7v2bl"; // Reemplaza con tu propia API key
    private String url = "https://www.datos.gov.co/resource/hk5x-635y.json?$limit=30";


    private JSONArray jsonArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_list);
        jsonTextView = findViewById(R.id.jsonTextView);
        listICFES(jsonTextView);
    }
    private void listICFES(final TextView jsonTextView) {
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    jsonArray = response;

                    // Itera sobre el array y agrega la información al TextView
                    StringBuilder listaCompleta = new StringBuilder();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String institucionEducativa = jsonObject.getString("institucion_educativa");
                        listaCompleta.append("Institución Educativa: ").append(institucionEducativa).append("\n \n");
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