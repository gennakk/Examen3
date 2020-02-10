package com.app.examen3.sqlite;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.examen3.R;
import com.app.examen3.sqlite.DetalleLibro;
import com.app.examen3.sqlite.Libro;

/**
 * Actividad detalle para lanzar el detalle de un libro
 */
public class ActivityDetalle extends AppCompatActivity {

    public static final String EXTRA_LIBRO = "LIBRO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Libro libro = (Libro)getIntent().getSerializableExtra(EXTRA_LIBRO);

        DetalleLibro detalle = (DetalleLibro) getSupportFragmentManager().findFragmentById(R.id.detalleLibro);
        findViewById(R.id.detalleLibro).setVisibility(View.VISIBLE);

        detalle.mostrarDetalle(libro);


    }
}
