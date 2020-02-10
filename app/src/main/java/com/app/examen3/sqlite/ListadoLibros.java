package com.app.examen3.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.examen3.R;

/**
 * Clase listado libros, la cual muestra los libros dependiendo de los radio buttons, o no(en caso de ser llamada por BuscarLibros).
 */
public class ListadoLibros extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private static final String EXTRA_AUTOR = "AUTOR";
    private static final String EXTRA_EDITORIAL = "EDITORIAL";
    private RadioButton rdLeidos,rdNoLeidos,rdTodos;
    private SQLiteControlador sql;
    private ListView listView;
    private Button btnVolver;

    private Libro[] libros;

    private String autor ;
    private String editorial ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_libros);


        sql = new SQLiteControlador(this,"libros");

        //Dependiendo del radio button utilizará una query distinta
        rdLeidos = findViewById(R.id.rdLeidos);
        rdLeidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libros = sql.getLibrosLeidos();

                AdaptadorBiblioteca adaptadorBiblioteca = new AdaptadorBiblioteca(ListadoLibros.this, libros);
                listView.setAdapter(adaptadorBiblioteca);
                listView.setOnItemLongClickListener(ListadoLibros.this);
            }
        });
        rdNoLeidos = findViewById(R.id.rdNoLeidos);
        rdNoLeidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libros = sql.getLibrosNoLeidos();

                AdaptadorBiblioteca adaptadorBiblioteca = new AdaptadorBiblioteca(ListadoLibros.this, libros);
                listView.setAdapter(adaptadorBiblioteca);
                listView.setOnItemLongClickListener(ListadoLibros.this);
            }
        });
        rdTodos = findViewById(R.id.rdTodos);
        rdTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libros = sql.getLibros();

                AdaptadorBiblioteca adaptadorBiblioteca = new AdaptadorBiblioteca(ListadoLibros.this, libros);
                listView.setAdapter(adaptadorBiblioteca);
                listView.setOnItemLongClickListener(ListadoLibros.this);
            }
        });


        listView = findViewById(R.id.listview);

        //Volver
        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //En caso de ser llamada por BuscarLibros obtiene un array de libros, lo carga en listView y desactiva radiobuttons
        if ((String)getIntent().getStringExtra(EXTRA_AUTOR)!=null){
            autor= (String)getIntent().getStringExtra(EXTRA_AUTOR);
            libros = sql.getLibroAutor((String)getIntent().getStringExtra(EXTRA_AUTOR));
            rdLeidos.setVisibility(View.INVISIBLE);
            rdNoLeidos.setVisibility(View.INVISIBLE);
            rdTodos.setVisibility(View.INVISIBLE);

            AdaptadorBiblioteca adaptadorBiblioteca = new AdaptadorBiblioteca(ListadoLibros.this, libros);
            listView.setAdapter(adaptadorBiblioteca);
            listView.setOnItemLongClickListener(ListadoLibros.this);
        }
        if ((String)getIntent().getStringExtra(EXTRA_EDITORIAL)!=null){
            editorial = (String)getIntent().getStringExtra(EXTRA_EDITORIAL);
            libros = sql.getLibroEditorial((String)getIntent().getStringExtra(EXTRA_EDITORIAL));
            rdLeidos.setVisibility(View.INVISIBLE);
            rdNoLeidos.setVisibility(View.INVISIBLE);
            rdTodos.setVisibility(View.INVISIBLE);

            AdaptadorBiblioteca adaptadorBiblioteca = new AdaptadorBiblioteca(ListadoLibros.this, libros);
            listView.setAdapter(adaptadorBiblioteca);
            listView.setOnItemLongClickListener(ListadoLibros.this);
        }



    }


    //Lanza detalle cuando hay un LongClick
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        AdaptadorBiblioteca adaptadorBiblioteca = new AdaptadorBiblioteca(this, libros);
        Libro libro = adaptadorBiblioteca.getItem(position);

        Intent i = new Intent(this, ActivityDetalle.class);
        i.putExtra(ActivityDetalle.EXTRA_LIBRO, libro);

        startActivity(i);


        return false;
    }

    //Actualiza la lista dependiendo del radiobutton checkeado
    public void actualizar(){
        if(rdLeidos.isChecked()){
            libros = sql.getLibrosLeidos();

            AdaptadorBiblioteca adaptadorBiblioteca = new AdaptadorBiblioteca(ListadoLibros.this, libros);
            listView.setAdapter(adaptadorBiblioteca);
            listView.setOnItemLongClickListener(ListadoLibros.this);
        }
        else if(rdNoLeidos.isChecked()){
            libros = sql.getLibrosNoLeidos();

            AdaptadorBiblioteca adaptadorBiblioteca = new AdaptadorBiblioteca(ListadoLibros.this, libros);
            listView.setAdapter(adaptadorBiblioteca);
            listView.setOnItemLongClickListener(ListadoLibros.this);
        }

        else if(rdTodos.isChecked()){
            libros = sql.getLibros();

            AdaptadorBiblioteca adaptadorBiblioteca = new AdaptadorBiblioteca(ListadoLibros.this, libros);
            listView.setAdapter(adaptadorBiblioteca);
            listView.setOnItemLongClickListener(ListadoLibros.this);
        }else if (editorial!=null){
            libros = sql.getLibroEditorial(editorial);

            AdaptadorBiblioteca adaptadorBiblioteca = new AdaptadorBiblioteca(ListadoLibros.this, libros);
            listView.setAdapter(adaptadorBiblioteca);
            listView.setOnItemLongClickListener(ListadoLibros.this);
        }else if (autor != null){
            libros = sql.getLibroAutor(autor);

            AdaptadorBiblioteca adaptadorBiblioteca = new AdaptadorBiblioteca(ListadoLibros.this, libros);
            listView.setAdapter(adaptadorBiblioteca);
            listView.setOnItemLongClickListener(ListadoLibros.this);
        }

    }

    //Cuando vuelve de otras actividades lanza el método onResume y actualiza
    @Override
    protected void onResume() {
        super.onResume();
        actualizar();
    }
}
