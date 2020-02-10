package com.app.examen3.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.app.examen3.R;

import java.util.ListResourceBundle;

/**
 * Actividad para buscar libros dependiendo del título, autor, editorial.
 */
public class BuscarLibros extends AppCompatActivity {

    public static final String EXTRA_AUTOR = "AUTOR";
    public static final String EXTRA_EDITORIAL = "EDITORIAL";

    private RadioButton rdTitulo,rdAutor,rdEditorial;
    private Button btnBuscar;
    private EditText edBusqueda;
    private SQLiteControlador sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_libros);

        sql = new SQLiteControlador(this,"libros");

        rdTitulo = findViewById(R.id.rdTitulo);
        rdAutor = findViewById(R.id.rdAutor);
        rdEditorial = findViewById(R.id.rdEditorial);

        edBusqueda = findViewById(R.id.edBusqueda);

        //Dependiendo del radio button se lanzan diferentes actividades. Título solo lanza 1 libro
        // por lo que se lanza el detalle. El resto lanza la actividad listado
        btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdTitulo.isChecked()){
                    Libro libro = sql.getLibroTitulo(edBusqueda.getText().toString());
                    Intent i = new Intent(BuscarLibros.this, ActivityDetalle.class);
                    i.putExtra(ActivityDetalle.EXTRA_LIBRO, libro);

                    startActivity(i);

                }

                if(rdAutor.isChecked()){
                    String autor = edBusqueda.getText().toString();

                    Intent i = new Intent(BuscarLibros.this, ListadoLibros.class);
                    i.putExtra(EXTRA_AUTOR, autor);

                    startActivity(i);

                }

                if(rdEditorial.isChecked()){
                    String editorial = edBusqueda.getText().toString();

                    Intent i = new Intent(BuscarLibros.this, ListadoLibros.class);
                    i.putExtra(ActivityDetalle.EXTRA_LIBRO, editorial);

                    startActivity(i);
                }
            }
        });


    }
}
