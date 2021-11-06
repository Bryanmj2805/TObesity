package com.app.apptobesity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Detallespaciente extends AppCompatActivity implements View.OnClickListener{

    TextView viewDni, viewNombres,viewApellidos, viewTelefono, viewEdad, viewTalla, viewPeso, viewImc, viewCita, viewTratamiento;

    String dni;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallespaciente);
        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            dni = extras.getString("dni");
        }

        requestQueue = Volley.newRequestQueue(this);
        initUI();
        FetchPacientes();

    }

    private void initUI(){
        viewDni = findViewById(R.id.editDni);
        viewNombres = findViewById(R.id.editNombres);
        viewApellidos = findViewById(R.id.editApellidos);
        viewTelefono = findViewById(R.id.editTelefono);
        viewEdad = findViewById(R.id.editEdad);
        viewTalla = findViewById(R.id.editTalla);
        viewPeso = findViewById(R.id.editPeso);
        viewImc = findViewById(R.id.editImc);
        viewCita = findViewById(R.id.editCita);
        viewTratamiento = findViewById(R.id.editTratamiento);


        viewDni.setInputType(InputType.TYPE_NULL);
        viewNombres.setInputType(InputType.TYPE_NULL);
        viewApellidos.setInputType(InputType.TYPE_NULL);
        viewTelefono.setInputType(InputType.TYPE_NULL);
        viewEdad.setInputType(InputType.TYPE_NULL);
        viewTalla.setInputType(InputType.TYPE_NULL);
        viewPeso.setInputType(InputType.TYPE_NULL);
        viewImc.setInputType(InputType.TYPE_NULL);
        viewCita.setInputType(InputType.TYPE_NULL);
        viewTratamiento.setInputType(InputType.TYPE_NULL);
    }

    private void FetchPacientes(){
        String URL = "https://leathery-challenge.000webhostapp.com/tobesity/buscarpacientes.php?dni=" + dni;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String dni, nombres, apellidos, telefono, edad, talla, peso, imc, cita, tratamiento;
                        try {
                            dni = response.getString("dni");
                            nombres = response.getString("nombres");
                            apellidos = response.getString("apellidos");
                            telefono = response.getString("telefono");
                            edad = response.getString("edad");
                            talla = response.getString("talla");
                            peso = response.getString("peso");
                            imc = response.getString("imc");
                            cita = response.getString("cita");
                            tratamiento = response.getString("tratamiento");

                            viewDni.setText(dni);
                            viewNombres.setText(nombres);
                            viewApellidos.setText(apellidos);
                            viewTelefono.setText(telefono);
                            viewEdad.setText(edad);
                            viewTalla.setText(talla);
                            viewPeso.setText(peso);
                            viewImc.setText(imc);
                            viewCita.setText(cita);
                            viewTratamiento.setText(tratamiento);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Detallespaciente.this, "Paciente no registrado", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onClick(View v) {

    }
}