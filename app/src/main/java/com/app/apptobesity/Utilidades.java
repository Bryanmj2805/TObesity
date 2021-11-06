package com.app.apptobesity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Utilidades extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnSearch, btnListview, endlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilidades);

        btnAdd = findViewById(R.id.btnAdd);
        btnSearch = findViewById(R.id.btnSearch);
        btnListview = findViewById(R.id.btnListview);
        endlogin = findViewById(R.id.endlogin);

        btnAdd.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnListview.setOnClickListener(this);
        endlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if( id == R.id.btnAdd ){
            Intent intent = new Intent(this,Add.class);
            startActivity(intent);
        } else if ( id == R.id.btnSearch){
            Intent intent = new Intent(this, Fetch.class);
            startActivity(intent);
        } else if ( id == R.id.btnListview) {
            Intent intent = new Intent(this, ListPacientes.class);
            startActivity(intent);
        } else if ( id == R.id.endlogin) {
            SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
            preferences.edit().clear().commit();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    }
}