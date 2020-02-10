package com.app.examen3.sqlite;

import java.io.Serializable;

/**
 * Clase libro
 */
public class Libro implements Serializable {

    private int isbn;
    private String nombre;
    private String autor;
    private String editorial;
    private int numpag;
    private int leido;

    public Libro(){}

    public Libro(int isbn,String nombre,String autor,String editorial,int numpag,int leido) {
        this.isbn=isbn;
        this.nombre=nombre;
        this.autor=autor;
        this.editorial=editorial;
        this.numpag=numpag;
        this.leido =leido;
    }

    public int getIsbn() {return isbn;}
    public String getNombre() {return nombre;}
    public String getAutor() {return autor;}
    public String getEditorial() {return editorial;}
    public int getNumpag() {return numpag;}
    public int getLeido(){return leido;};

    public void setIsbn(int isbn) {this.isbn=isbn;}
    public void setNombre(String nombre) {this.nombre=nombre;}
    public void setAutor(String autor) {this.autor=autor;}
    public void setEditorial(String editorial) {this.editorial=editorial;}
    public void setNumpag(int numpag) {this.numpag=numpag;}
    public void setLeido (int leido){this.leido=leido;}

    @Override
    public String toString() {
        return "Libro{" +
                "isbn=" + isbn +
                ", nombre='" + nombre + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", numpag=" + numpag +
                ", leido=" + leido +
                '}';
    }
}
