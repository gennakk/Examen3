package com.app.examen3.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Objeto temporal
 */
public class Temporal {

    //root -> prediccion -> dia...
    private Date fecha;

    private String estadocielo; //estado_cielo@descripcion

    private int tempmin;        //temperatura -> minima
    private int tempmax;        //temperatura -> maxima

    public Temporal() {}

    public String getFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha);
    }

    public void setFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fecha = sdf.parse(fecha);
        } catch (ParseException e) {
            this.fecha = null;
        }
    }


    public String getEstadocielo() {
        return estadocielo;
    }

    public void setEstadocielo(String estadocielo) {
        this.estadocielo = estadocielo;
    }





    public int getTempmin() {
        return tempmin;
    }

    public void setTempmin(int tempmin) {
        this.tempmin = tempmin;
    }

    public int getTempmax() {
        return tempmax;
    }

    public void setTempmax(int tempmax) {
        this.tempmax = tempmax;
    }

    @Override
    public String toString() {
        return "Temporal{" +
                "fecha=" + fecha +
                ", estadocielo='" + estadocielo + '\'' +
                ", tempmin=" + tempmin +
                ", tempmax=" + tempmax +
                '}';
    }
}
