package com.app.examen3.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.app.examen3.R;

/**
 * Actividad para crear un nuevo libro
 */
public class NuevoLibro extends AppCompatActivity {

    private EditText edTitulo,edAutor,edIsbn,edEditorial,edNumPag;
    private CheckBox chLeido;
    private Button btnAniadir,btnCancelar,btnVolver;

    private SQLiteControlador sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_libro);

        sql = new SQLiteControlador(this,"libros");

        edTitulo = findViewById(R.id.edTitulo);
        edAutor = findViewById(R.id.edAutor);
        edIsbn = findViewById(R.id.edIsbn);
        edEditorial = findViewById(R.id.edEditorial);
        edNumPag = findViewById(R.id.edNumPag);


        chLeido = findViewById(R.id.chLeido);

        //Añadir libro llama a el controlador y añade, despues limpia los EditText
        btnAniadir = findViewById(R.id.btnAniadir);
        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sql.addLibro(cogerLibro());
                    Toast.makeText(getBaseContext(),"Libro añadido",Toast.LENGTH_SHORT).show();
                }catch (SQLiteConstraintException e){
                    Toast.makeText(getBaseContext(),"ISBN repetido",Toast.LENGTH_SHORT).show();
                }
                limpiar();
            }
        });

        btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //Crea el libro con los valores del EditText
    private Libro cogerLibro() {
        Libro libro = new Libro();


        libro.setNombre(edTitulo.getText().toString());
        libro.setAutor(edAutor.getText().toString());
        libro.setIsbn(Integer.parseInt(edIsbn.getText().toString()));
        libro.setEditorial(edEditorial.getText().toString());
        libro.setNumpag(Integer.parseInt(edNumPag.getText().toString()));
        if(chLeido.isChecked())
            libro.setLeido(0);
        else
            libro.setLeido(1);
        return libro;
    }

    //Limpia los EditText
    private void limpiar() {
        edNumPag.setText("");
        edEditorial.setText("");
        edIsbn.setText("");
        edAutor.setText("");
        edTitulo.setText("");
        chLeido.setChecked(false);


    }
}
