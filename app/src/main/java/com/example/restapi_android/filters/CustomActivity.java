package com.example.restapi_android.filters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;


import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restapi_android.R;
import com.example.restapi_android.activities.FilteredCustomList;
import com.example.restapi_android.activities.FilteredSendesdList;

public class CustomActivity extends AppCompatActivity {

    // Define las opciones del Spinner
    Spinner spinner;
    TextView inputSize;

    Button btnSize;

    private String seleccion ="municipio";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        spinner = findViewById(R.id.spinner);
        inputSize = findViewById(R.id.customInput);
        btnSize = findViewById(R.id.custom);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Configurar un listener para el Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtener el valor seleccionado
                seleccion = parentView.getItemAtPosition(position).toString();

                // Hacer algo con el valor seleccionado, por ejemplo, mostrarlo en un Toast
                Toast.makeText(getApplicationContext(), "Seleccionado: " + seleccion, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                seleccion = "municipio";
            }
        });
        btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = inputSize.getText().toString();
                sendParameters(seleccion,value);
            }
        });

    }

    private void sendParameters(String column,String value) {
        Intent intent = new Intent(this, FilteredCustomList.class);
        intent.putExtra("COLUMN", column); // "SIZE_EXTRA" es una clave única para identificar el extra
        intent.putExtra("VALUE", value);
        startActivity(intent);
    }
}