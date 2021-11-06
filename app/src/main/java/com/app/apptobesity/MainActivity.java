package com.app.apptobesity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnMedico, btnPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMedico = findViewById(R.id.btnMedico);
        btnPaciente = findViewById(R.id.btnPaciente);

        btnMedico.setOnClickListener(this);
        btnPaciente.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if( id == R.id.btnMedico ){
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
        }else if (id == R.id.btnPaciente ){
            Intent intent = new Intent(this,Busquedapaciente.class);
            startActivity(intent);
        }
    }
}