package com.app.examen3.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase helper que crea la BBDD
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("DROP TABLE IF EXISTS Libros");
        db.execSQL("CREATE TABLE Libros (isbn INTEGER PRIMARY KEY , nombre TEXT, autor TEXT, editorial TEXT, numpag INTEGER, leido INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        db.execSQL("DROP TABLE IF EXISTS Libros");
        db.execSQL("CREATE TABLE Libros (isbn INTEGER PRIMARY KEY , nombre TEXT, autor TEXT, editorial TEXT, numpag INTEGER, leido INTEGER)");
    }
}

