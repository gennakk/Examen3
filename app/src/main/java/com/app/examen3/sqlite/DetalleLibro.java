package com.app.examen3.sqlite;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.examen3.R;

/**
 * Fragment encargado de mostrar los datos del libro, es llamado por ActividadDetalle
 */
public class DetalleLibro extends Fragment {


    private TextView tvTitulo,tvAutor,tvIsbn,tvEditorial,tvPaginas,tvLeido;

    private Button btnEliminar,btnCancelar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_libro, container,false);
    }

    public void mostrarDetalle (final Libro libro) {


         // Inflate the layout for this fragment
        tvAutor = getView().findViewById(R.id.tvAutorLibro);
        tvAutor.setText(libro.getAutor());
        tvTitulo = getView().findViewById(R.id.tvTituloLibro);
        tvTitulo.setText(libro.getNombre());
        tvIsbn = getView().findViewById(R.id.tvIsbnLibro);
        tvIsbn.setText(libro.getIsbn()+"");
        tvEditorial = getView().findViewById(R.id.tvEditorialLibro);
        tvEditorial.setText(libro.getEditorial());
        tvPaginas = getView().findViewById(R.id.tvPaginasLibro);
        tvPaginas.setText(libro.getNumpag()+" Paginas");
        tvLeido = getView().findViewById(R.id.tvLeidoLibro);
        if(libro.getLeido() == 0){
            tvLeido.setText("Leido LEIDO");
        }else{
            tvLeido.setText("Leido NO LEIDO");
        }


        btnCancelar = getView().findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        btnEliminar = getView().findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteControlador sql = new SQLiteControlador(getContext(),"libros");

                sql.delLibro(libro);
                Toast.makeText(getContext(),"Libro borrado",Toast.LENGTH_SHORT).show();

                getActivity().onBackPressed();
            }
        });





    }



}
