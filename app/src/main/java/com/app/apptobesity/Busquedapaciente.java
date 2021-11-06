package com.app.apptobesity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Busquedapaciente extends AppCompatActivity implements View.OnClickListener{

    EditText txtDni;
    Button btnFetch, btnNotificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busquedapaciente);
        txtDni = findViewById(R.id.txtDni);
        btnFetch = findViewById(R.id.btnFetch);
        btnNotificacion = findViewById(R.id.btnNotificacion);
        btnFetch.setOnClickListener(this);
        btnNotificacion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if( id == R.id.btnFetch ){
            Intent intent = new Intent(this,Detallespaciente.class);
            intent.putExtra("dni",txtDni.getText().toString().trim());
            startActivity(intent);
        } else if ( id == R.id.btnNotificacion) {
            Intent intent = new Intent(this,Notificacion.class);
            startActivity(intent);
        }
    }
}