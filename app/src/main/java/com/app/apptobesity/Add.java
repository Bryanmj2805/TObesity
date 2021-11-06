package com.app.apptobesity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Add extends AppCompatActivity implements View.OnClickListener{

    EditText txtDni, txtNombres, txtApellidos, txtTelefono, txtEdad, txtTalla, txtPeso, txtCita, txtImc, txtTratamiento, etId;
    Button btnCreate;
    DatePickerDialog.OnDateSetListener setListener;
    RequestQueue requestQueue;

    private static final String URL1 = "https://leathery-challenge.000webhostapp.com/tobesity/registropacientes.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        requestQueue = Volley.newRequestQueue(this);
        initUI();
        Calendario();
        btnCreate.setOnClickListener(this);

    }
    private void Calendario(){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        txtCita.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Add.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        txtCita.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    private void initUI(){
        //EditText
        txtDni = findViewById(R.id.etDni);
        txtNombres = findViewById(R.id.etNombres);
        txtApellidos = findViewById(R.id.etApellidos);
        txtTelefono = findViewById(R.id.etPhone);
        txtEdad = findViewById(R.id.etEdad);
        txtTalla = findViewById(R.id.etTalla);
        txtPeso = findViewById(R.id.etPeso);
        txtCita = findViewById(R.id.etCita);
        txtTratamiento = findViewById(R.id.etTratamiento);
        //Buttons
        btnCreate = findViewById(R.id.btnCreate);

        txtCita.setInputType(InputType.TYPE_NULL);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnCreate){
            String dni = txtDni.getText().toString().trim();
            String nombres = txtNombres.getText().toString().trim();
            String apellidos = txtApellidos.getText().toString().trim();
            String telefono = txtTelefono.getText().toString().trim();
            String edad = txtEdad.getText().toString().trim();
            String talla = txtTalla.getText().toString().trim();
            String peso = txtPeso.getText().toString().trim();
            Double imcAux = (Double.parseDouble(peso)/(Double.parseDouble(talla)*Double.parseDouble(talla)));
            String imc = ""+imcAux;
            String cita = txtCita.getText().toString().trim();
            String tratamiento = txtTratamiento.getText().toString().trim();


            createUser(dni, nombres, apellidos, telefono, edad, talla, peso, imc,cita, tratamiento);

        }
    }

    private void createUser(final String dni, final String nombres, final String apellidos, final String telefono, final String edad, final String talla, final String peso, final String imc, final String cita, final String tratamiento){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1,
                new Response.Listener<String>(){
                    //Mensaje de exito
                    @Override
                    public void onResponse(String response){
                        Toast.makeText(Add.this, "Correct", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("dni", dni);
                params.put("nombres", nombres);
                params.put("apellidos", apellidos);
                params.put("telefono", telefono);
                params.put("edad", edad);
                params.put("talla", talla);
                params.put("peso", peso);
                params.put("imc", imc);
                params.put("cita", cita);
                params.put("tratamiento", tratamiento);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}