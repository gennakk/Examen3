package com.app.examen3.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.examen3.MainActivity;
import com.app.examen3.R;

/**
 * Menú con las opciones para los libros
 */
public class MenuBases extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bases);


        Button btnBases = findViewById(R.id.btnNuevo);
        btnBases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuBases.this, NuevoLibro.class);
                startActivity(i);
            }
        });

        Button btnListado = findViewById(R.id.btnListado);
        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuBases.this, ListadoLibros.class);
                startActivity(i);
            }
        });

        Button btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuBases.this, BuscarLibros.class);
                startActivity(i);
            }
        });

        Button btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
