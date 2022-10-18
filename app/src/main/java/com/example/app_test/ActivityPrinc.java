package com.example.app_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityPrinc extends AppCompatActivity {
    Button btnadd, btnlist, btncombo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princ);

        btnadd = (Button) findViewById(R.id.btnadd);
        btnlist = (Button) findViewById(R.id.btnlist);
        btncombo = (Button) findViewById(R.id.btncombo);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityIngresar.class);
                startActivity(intent);
            }
        });

        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityList.class);
                startActivity(intent);
            }
        });

        btncombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityCombo2.class);
                startActivity(intent);
            }
        });
    }
}