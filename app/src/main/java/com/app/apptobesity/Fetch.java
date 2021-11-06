package com.app.apptobesity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Fetch extends AppCompatActivity implements View.OnClickListener{

    EditText txtDni;
    Button btnFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        txtDni = findViewById(R.id.txtDni);
        btnFetch = findViewById(R.id.btnFetch);

        btnFetch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if( id == R.id.btnFetch ){
            Intent intent = new Intent(this,Fetchdetail.class);
            intent.putExtra("dni",txtDni.getText().toString().trim());
            startActivity(intent);
        }
    }
}