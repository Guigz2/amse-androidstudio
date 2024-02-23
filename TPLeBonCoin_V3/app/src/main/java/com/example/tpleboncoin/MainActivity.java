package com.example.tpleboncoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonDisplayList = findViewById(R.id.buttonDisplayList);
        Button buttonAddMaterial = findViewById(R.id.buttonAddMaterial);

        buttonDisplayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdListViewActivity.class);
                startActivity(intent);
            }
        });

        buttonAddMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdAddActivity.class);
                startActivity(intent);
            }
        });
    }
}

