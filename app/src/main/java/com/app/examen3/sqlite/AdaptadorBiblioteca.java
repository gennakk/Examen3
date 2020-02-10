package com.app.examen3.sqlite;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.examen3.R;

/**
 * Adaptador para la lista de libros
 */
class AdaptadorBiblioteca extends ArrayAdapter<Libro> {

    private Libro[] datosLibro;
    public AdaptadorBiblioteca(@NonNull Context context, Libro[] datos) {
        super(context, R.layout.adaptador_biblioteca, datos);
        this.datosLibro = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.adaptador_biblioteca, null);

        String nombre = datosLibro[position].getNombre();
        String autor = datosLibro[position].getAutor();


        TextView tvNombre = item.findViewById(R.id.nombreLibro);
        tvNombre.setText(nombre);



        TextView tvAutor = item.findViewById(R.id.autorLibro);
        tvAutor.setText("Autor: "+autor);


        return (item);
    }
}