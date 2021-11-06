package com.app.apptobesity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.apptobesity.dependencias.Pacientes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Adaptador extends RecyclerView.Adapter<Adaptador.PacienteViewHolder>{

    private Context mCtx;
    private List<Pacientes> pacientesListar;
    //private List<Pacientes> originalItems;

    public Adaptador(Context mCtx, List<Pacientes> pacientesListar) {
        this.mCtx = mCtx;
        this.pacientesListar = pacientesListar;
        //this.originalItems = new ArrayList<>();
        //originalItems.addAll(pacientesListar);
    }

    @NonNull
    @Override
    public Adaptador.PacienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //relacionando la vista
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout_pacientes,null);
        return new Adaptador.PacienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.PacienteViewHolder holder, int position) {
        Pacientes paciente = pacientesListar.get(position);
        holder.listDni.setText(String.valueOf(paciente.getDni()));
        holder.listNombres.setText(paciente.getNombres());
        holder.listApellidos.setText(paciente.getApellidos());
    }

    @Override
    public int getItemCount() {
        //tamaño de la lista
        return pacientesListar.size();
    }
    /*
    public void filter(String viewSearch){
        if (viewSearch.length() == 0 ){
            pacientesListar.clear();
            pacientesListar.addAll(originalItems);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pacientesListar.clear();
                List<Pacientes> collect = originalItems.stream()
                        .filter(i-> i.getNombres().toLowerCase().contains(viewSearch))
                        .collect(Collectors.toList());

                pacientesListar.addAll(collect);
            } else {
                pacientesListar.clear();
                for (Pacientes p: originalItems) {
                    if (p.getNombres().toLowerCase().contains(viewSearch)) {
                        pacientesListar.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }*/

    public class PacienteViewHolder extends RecyclerView.ViewHolder {
        //variables
        TextView listDni, listNombres, listApellidos;
        ConstraintLayout milayout;
        public PacienteViewHolder(@NonNull View itemView) {
            super(itemView);
            //relacionando
            listDni = itemView.findViewById(R.id.listDni);
            listNombres = itemView.findViewById(R.id.listNombres);
            listApellidos = itemView.findViewById(R.id.listApellidos);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Fetchdetail.class);
                    intent.putExtra("dni",pacientesListar.get(getAdapterPosition()).getDni());
                    context.startActivity(intent);
                }
            });
        }
    }
}
