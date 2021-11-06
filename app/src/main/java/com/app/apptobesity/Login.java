package com.app.apptobesity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button btnAccess;
    EditText userpws, userlogin;
    String usuario, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userlogin = findViewById(R.id.userlogin);
        userpws = findViewById(R.id.userpws);
        btnAccess = findViewById(R.id.btnAccess);

        btnAccess.setOnClickListener(this);
        recuperarPreferencias();
    }

    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    guardarPreferencias();
                    Intent intent = new Intent(getApplicationContext(),Utilidades.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("dni",usuario);
                params.put("clave", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if( id == R.id.btnAccess ){
            usuario = userlogin.getText().toString();
            password = userpws.getText().toString();
            if(!usuario.isEmpty() && !password.isEmpty()){
                validarUsuario("https://leathery-challenge.000webhostapp.com/tobesity/login.php");
            } else {
                Toast.makeText(Login.this, "Existen campos en blanco", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("dni", usuario);
        editor.putString("clave", password);
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    private void recuperarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        userlogin.setText(preferences.getString("dni",""));
        userpws.setText(preferences.getString("clave", ""));
    }
}