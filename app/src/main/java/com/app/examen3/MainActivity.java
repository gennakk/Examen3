package com.app.examen3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.app.examen3.sqlite.MenuBases;
import com.app.examen3.xml.ActivityTiempo;

/**
 * Clase Main, encargada de hacer el menú para el resto
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lanzar actividad con el menú para BBDD SQlite
        Button btnBases = findViewById(R.id.btnBases);
        btnBases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuBases.class);
                startActivity(i);
            }
        });

        //Lanzar actividad con Actividad para el tiempo XML
        Button btnXML = findViewById(R.id.btnXML);
        btnXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityTiempo.class);
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
