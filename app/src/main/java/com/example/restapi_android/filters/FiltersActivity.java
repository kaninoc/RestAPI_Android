package com.example.restapi_android.filters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restapi_android.R;
import com.example.restapi_android.activities.FilteredSendesdList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FiltersActivity extends AppCompatActivity {

    TextView inputSize;

    Button btnSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        inputSize = findViewById(R.id.lengthInput);
        btnSize = findViewById(R.id.size);

        btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String size = inputSize.getText().toString();
                sendParameters(size);
            }
        });
    }

    private void sendParameters(String size) {
        Intent intent = new Intent(this, FilteredSendesdList.class);
        intent.putExtra("SIZE_EXTRA", size); // "SIZE_EXTRA" es una clave única para identificar el extra
        startActivity(intent);
    }
}