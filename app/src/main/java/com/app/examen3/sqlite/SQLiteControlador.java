package com.app.examen3.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Controlador de la BBDD, tiene las querys necesarias
 */
public class SQLiteControlador {

    private String nombrebd;
    private Context context;
    public SQLiteControlador(Context context) {
        this.context = context;
        nombrebd = "default";
    }

    public SQLiteControlador(Context context, String nombrebd) {
        this.context = context;
        this.nombrebd = nombrebd;
    }

    private SQLiteHelper getSQLiteHelper() {
        return new SQLiteHelper(context, nombrebd, null, 2);
    }


    //Obtiene todos los libros
    public Libro[] getLibros() {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT COUNT(isbn) FROM Libros",null);
        c.moveToFirst();
        Libro[] libros = new Libro[c.getInt(0)];

        c = db.rawQuery("SELECT isbn,nombre,autor,editorial,numpag,leido FROM Libros",null);
        int x = 0;
        while (c.moveToNext()) {

            Libro libro = new Libro(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getInt(4),
                    c.getInt(5)
            );
            libros[x]=libro;
            x++;
        }
        c.close();
        db.close();

        return libros;
    }

    //Obtiene solo los libros leidos
    public Libro[] getLibrosLeidos() {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT COUNT(isbn) FROM Libros WHERE leido != 1",null);
        c.moveToFirst();
        Libro[] libros = new Libro[c.getInt(0)];

        c = db.rawQuery("SELECT isbn,nombre,autor,editorial,numpag,leido FROM Libros WHERE leido != 1",null);
        int x = 0;
        while (c.moveToNext()) {

            Libro libro = new Libro(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getInt(4),
                    c.getInt(5)
            );
            libros[x]=libro;
            x++;
        }
        c.close();
        db.close();

        return libros;
    }

    //Obtiene solo los libros no leidos
    public Libro[] getLibrosNoLeidos() {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT COUNT(isbn) FROM Libros WHERE leido = 1",null);
        c.moveToFirst();
        Libro[] libros = new Libro[c.getInt(0)];

        c = db.rawQuery("SELECT isbn,nombre,autor,editorial,numpag,leido FROM Libros WHERE leido = 1",null);
        int x = 0;
        while (c.moveToNext()) {

            Libro libro = new Libro(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getInt(4),
                    c.getInt(5)
            );
            libros[x]=libro;
            x++;
        }
        c.close();
        db.close();

        return libros;
    }

    //Añade un libro
    public void addLibro(Libro libro) throws SQLiteConstraintException{
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();


            db.execSQL("INSERT INTO Libros(isbn,nombre,autor,editorial,numpag,leido) VALUES('"
                    + libro.getIsbn() + "','"
                    + libro.getNombre() + "','"
                    + libro.getAutor() + "','"
                    + libro.getEditorial() + "','"
                    + libro.getNumpag() + "','"
                    + libro.getLeido() + "')");



        db.close();
    }

    //Borra un libro
    public void delLibro(Libro libro) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        db.execSQL("DELETE FROM Libros WHERE isbn="+libro.getIsbn());

        db.close();

    }

    //Obtiene libro por el titulo
    public Libro getLibroTitulo(String nombre) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT COUNT(isbn) FROM Libros WHERE nombre = '"+nombre+"'",null);
        c.moveToFirst();
        Libro[] libros = new Libro[c.getInt(0)];

        c = db.rawQuery("SELECT isbn,nombre,autor,editorial,numpag,leido FROM Libros WHERE nombre ='"+nombre+"'",null);
        int x = 0;
        while (c.moveToNext()) {

            Libro libro = new Libro(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getInt(4),
                    c.getInt(5)
            );
            libros[x]=libro;
            x++;
        }
        c.close();
        db.close();

        return libros[0];
    }

    //Obtiene libro por autor
    public Libro[] getLibroAutor(String autor) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT COUNT(isbn) FROM Libros WHERE autor = '"+autor+"'",null);
        c.moveToFirst();
        Libro[] libros = new Libro[c.getInt(0)];

        c = db.rawQuery("SELECT isbn,nombre,autor,editorial,numpag,leido FROM Libros WHERE autor ='"+autor+"'",null);
        int x = 0;
        while (c.moveToNext()) {

            Libro libro = new Libro(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getInt(4),
                    c.getInt(5)
            );
            libros[x]=libro;
            x++;
        }
        c.close();
        db.close();

        return libros;
    }

    //Obtiene libro por editorial
    public Libro[] getLibroEditorial(String editorial) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT COUNT(isbn) FROM Libros WHERE editorial = '"+editorial+"'",null);
        c.moveToFirst();
        Libro[] libros = new Libro[c.getInt(0)];

        c = db.rawQuery("SELECT isbn,nombre,autor,editorial,numpag,leido FROM Libros WHERE editorial ='"+editorial+"'",null);
        int x = 0;
        while (c.moveToNext()) {

            Libro libro = new Libro(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getInt(4),
                    c.getInt(5)
            );
            libros[x]=libro;
            x++;
        }
        c.close();
        db.close();

        return libros;
    }



}
