package com.app.apptobesity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.WorkManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Notificacion extends AppCompatActivity {

    Button selefecha, selehora;
    TextView tvfecha, tvhora;
    Button btnguardar, btneliminar;

    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    private int minutos, hora, dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);

        selefecha = findViewById(R.id.btnselefecha);
        selehora = findViewById(R.id.btnselehora);
        tvfecha = findViewById(R.id.tvfecha);
        tvhora = findViewById(R.id.tvhora);
        btnguardar = findViewById(R.id.btnguardar);
        btneliminar = findViewById(R.id.btneliminar);

        selefecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anio = actual.get (Calendar. YEAR);
                mes = actual.get(Calendar.MONTH);
                dia = actual.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        calendar.set(Calendar.DAY_OF_MONTH, d);
                        calendar.set(Calendar.MONTH, m);
                        calendar.set(Calendar.YEAR, y);

                        SimpleDateFormat format = new SimpleDateFormat(  "dd/MM/yyyy");
                        String strDate = format.format(calendar.getTime());
                        tvfecha.setText(strDate);
                    }
                }, anio, mes, dia);
                datePickerDialog.show();
            }
        });
        selehora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora = actual.get(Calendar.HOUR_OF_DAY);
                minutos = actual.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int h, int m) {
                        calendar.set(Calendar.HOUR_OF_DAY, h);
                        calendar.set(Calendar.MINUTE, m);

                        tvhora.setText(String.format("%02d:%02d",h,m));
                    }
                }, hora, minutos, true);
                timePickerDialog.show();
            }
        });
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = generateKey();
                Long AlertTime = calendar.getTimeInMillis() - System.currentTimeMillis();
                int random = (int)(Math.random()*50+1);

                Data data = GuardarData("Notificacion TObesity", "Recuerda hoy tienes un control médico", random);
                Workmanagernoti.GuardarNoti(AlertTime,data,tag);
                Toast.makeText(Notificacion.this, "Alarma Guardada.", Toast.LENGTH_SHORT).show();
            }
        });
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarNoti("tag1");
            }
        });
    }

    private void EliminarNoti(String tag){
        WorkManager.getInstance(this).cancelAllWorkByTag(tag);
        Toast.makeText(Notificacion.this, "Alarma Eliminada.", Toast.LENGTH_SHORT).show();
    }

    private String generateKey(){
        return UUID.randomUUID().toString();
    }

    private Data GuardarData(String titulo, String detalle, int id_noti){
        return new Data.Builder()
                .putString("titulo", titulo)
                .putString("titulo", detalle)
                .putInt("id_noti", id_noti).build();
    }
}