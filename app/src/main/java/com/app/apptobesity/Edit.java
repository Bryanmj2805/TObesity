package com.app.apptobesity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Edit extends AppCompatActivity implements View.OnClickListener{

    TextView editDni;
    EditText editNombres, editApellidos, editTelefono, editEdad, editTalla, editPeso, editImc, editCita, editTratamiento;
    Button btnEdit;
    String dni, nombres, apellidos, telefono, edad, talla, peso, imc, cita, tratamiento;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            dni = extras.getString("dni");
            nombres = extras.getString("nombres");
            apellidos = extras.getString("apellidos");
            telefono = extras.getString("telefono");
            edad = extras.getString("edad");
            talla = extras.getString("talla");
            peso = extras.getString("peso");
            imc = extras.getString("imc");
            cita = extras.getString("cita");
            tratamiento = extras.getString("tratamiento");
        }
        requestQueue = Volley.newRequestQueue(this);
        initUI();

        btnEdit.setOnClickListener(this);
    }

    private void initUI(){
        editDni = findViewById(R.id.editDni);
        editNombres = findViewById(R.id.editNombres);
        editApellidos = findViewById(R.id.editApellidos);
        editTelefono = findViewById(R.id.editTelefono);
        editEdad = findViewById(R.id.editEdad);
        editTalla = findViewById(R.id.editTalla);
        editPeso = findViewById(R.id.editPeso);
        editImc = findViewById(R.id.editImc);
        editCita = findViewById(R.id.editCita);
        editTratamiento = findViewById(R.id.editTratamiento);
        btnEdit = findViewById(R.id.floatbtnDelete);

        editDni.setInputType(InputType.TYPE_NULL);
        editDni.setText(dni);
        editNombres.setText(nombres);
        editApellidos.setText(apellidos);
        editTelefono.setText(telefono);
        editEdad.setText(edad);
        editTalla.setText(talla);
        editPeso.setText(peso);
        editImc.setText(imc);
        editCita.setText(cita);
        editTratamiento.setText(tratamiento);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if ( id == R.id.floatbtnDelete){
            String dni = editDni.getText().toString();
            String nombres = editNombres.getText().toString();
            String apellidos = editApellidos.getText().toString();
            String telefono = editTelefono.getText().toString();
            String edad = editEdad.getText().toString();
            String talla = editTalla.getText().toString();
            String peso = editPeso.getText().toString();
            String imc = editImc.getText().toString();
            String cita = editCita.getText().toString();
            String tratamiento = editTratamiento.getText().toString();

            updatePaciente(dni,nombres,apellidos,telefono, edad, talla, peso, imc ,cita, tratamiento);
        }
    }

    private void updatePaciente(String dni, String nombres, String apellidos, String telefono, String edad, String talla, String peso, String imc, String cita, String tratamiento) {
        String URL = "https://leathery-challenge.000webhostapp.com/tobesity/editarpacientes.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit.this, "Datos actualizados", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

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