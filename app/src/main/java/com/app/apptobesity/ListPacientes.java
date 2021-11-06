package com.app.apptobesity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.apptobesity.dependencias.Pacientes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListPacientes extends AppCompatActivity /*implements SearchView.OnQueryTextListener*/{

    private static final String URL = "https://leathery-challenge.000webhostapp.com/tobesity/listarviewpacientes.php";

    private List<Pacientes> pacientesListar;
    private RecyclerView recyclerPacientes;

    private SearchView viewSearch;
    //private Adaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pacientes);

        //viewSearch = findViewById(R.id.viewSearch);

        recyclerPacientes = (RecyclerView) findViewById(R.id.recyclerPacientes);
        recyclerPacientes.setHasFixedSize(true);
        recyclerPacientes.setLayoutManager(new LinearLayoutManager(this));
        pacientesListar = new ArrayList<>();


        cargarPacientes();
        //initListener();

    }

    private void cargarPacientes() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //String -> jsonarrayobject
                            JSONArray array = new JSONArray(response);
                            //navegacion sobre objetos
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject paciente = array.getJSONObject(i);

                                pacientesListar.add(new Pacientes(
                                        paciente.getString("dni"),
                                        paciente.getString("nombres"),
                                        paciente.getString("apellidos"),
                                        paciente.getString("telefono"),
                                        paciente.getString("edad"),
                                        paciente.getDouble("talla"),
                                        paciente.getDouble("peso"),
                                        paciente.getDouble("imc"),
                                        paciente.getString("cita"),
                                        paciente.getString("tratamiento")
                                ));
                            }
                            Adaptador adaptador = new Adaptador(ListPacientes.this, pacientesListar);
                            recyclerPacientes.setAdapter(adaptador);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    /*private void initListener(){
        viewSearch.setOnQueryTextListener(this);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }*/
}