package com.example.restapi_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restapi_android.activities.FullList;
import com.example.restapi_android.activities.OrderList;
import com.example.restapi_android.filters.CustomActivity;
import com.example.restapi_android.filters.FiltersActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAll, btnOrder, btnSize, btnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAll = findViewById(R.id.showAll);
        btnOrder = findViewById(R.id.showOrder);
        btnSize = findViewById(R.id.sizeButton);
        btnCustom = findViewById(R.id.customButton);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllList();
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrderList();
            }
        });

        btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLimitList();
            }
        });

        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomList();
            }
        });

    }

    private void showAllList(){
        Intent intent = new Intent(this, FullList.class);
        startActivity(intent);
    }

    private void showOrderList(){
        Intent intent = new Intent(this, OrderList.class);
        startActivity(intent);
    }

    private void showLimitList(){
        Intent intent = new Intent(this, FiltersActivity.class);
        startActivity(intent);
    }

    private void showCustomList(){
        Intent intent = new Intent(this, CustomActivity.class);
        startActivity(intent);
    }


}
