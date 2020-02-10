package com.app.examen3.xml;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Parseador de los XML
 */
public class RssParserDOMtemperatura {

    private URL rssURL;

    public RssParserDOMtemperatura(String url){
        try{
            this.rssURL = new URL (url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    //Parsea la URL de vitoria y devuelve un objeto temporal con los datos
    public Temporal parseVitoria() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Temporal temporal = new Temporal();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.getInputStream());
            Element root = dom.getDocumentElement();

            //Localizamos todos los elemntos <pronostico_dias>
            NodeList items = root.getElementsByTagName("pronostico_dias");



            //ETIQUETA DIA
            Node dia1 = items.item(0);


            Node dia = dia1.getFirstChild();

            //OBTENEMOS LOS DATOS DE DIA
            NodeList datosTemporal = dia.getChildNodes();

            //RECORREMOS CADA ELEMENTO y metemos datos en objeto TEMPORAL
            for (int j=0; j<datosTemporal.getLength(); j++){
                //OBTENER NOMBRE ETIQUETA
                Node dato = datosTemporal.item(j);
                String etiqueta = dato.getNodeName();
                if (etiqueta.equals("fecha")) {
                    String texto = obtenerTexto(dato);
                    if (texto.equals("")) temporal.setFecha("");
                    else temporal.setFecha(texto);
                } else if (etiqueta.equals("temp_maxima")) {
                    String texto = obtenerTexto(dato);
                    if (texto.equals("")) temporal.setTempmax(0);
                    else temporal.setTempmax(Integer.parseInt(texto));
                } else if (etiqueta.equals("temp_minima")) {
                    String texto = obtenerTexto(dato);
                    if (texto.equals("")) temporal.setTempmin(0);
                    else temporal.setTempmin(Integer.parseInt(texto));
                }else if (etiqueta.equals("texto")) {
                    String texto = obtenerTexto(dato);
                    if (texto.equals("")) temporal.setEstadocielo("");
                    else temporal.setEstadocielo(texto);
                }
            }




        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }



        return temporal;

    }

    //Parsea las URL de Bilbao y Donosti, devuelve un objeto temporal cno los datos
    public Temporal parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Temporal temporal = new Temporal();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.getInputStream());
            Element root = dom.getDocumentElement();

            //Localizamos todos los elemntos <day1>
            NodeList items = root.getElementsByTagName("day1");



            //ETIQUETA DIA
            Node dia = items.item(0);


            //OBTENEMOS LOS DATOS DE DIA
            NodeList datosTemporal = dia.getChildNodes();

            //RECORREMOS CADA ELEMENTO y metemos los datos en objeto temporal
            for (int j=0; j<datosTemporal.getLength(); j++){
                //OBTENER NOMBRE ETIQUETA
                Node dato = datosTemporal.item(j);
                String etiqueta = dato.getNodeName();

                if (etiqueta.equals("date")) {
                    String texto = obtenerTexto(dato);
                    if (texto.equals("")) temporal.setFecha("");
                    else temporal.setFecha(texto);
                } else if (etiqueta.equals("temperature_max")) {
                    String texto = obtenerTexto(dato);
                    if (texto.equals("")) temporal.setTempmax(0);
                    else temporal.setTempmax(Integer.parseInt(texto));
                } else if (etiqueta.equals("temperature_min")) {
                    String texto = obtenerTexto(dato);
                    if (texto.equals("")) temporal.setTempmin(0);
                    else temporal.setTempmin(Integer.parseInt(texto));
                }else if (etiqueta.equals("text")) {
                    String texto = obtenerTexto(dato);
                    if (texto.equals("")) temporal.setEstadocielo("");
                    else temporal.setEstadocielo(texto);
                }
            }




        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }


        return temporal;

    }


    //Obtenemos el texto de los elementos
    public String obtenerTexto (Node dato) {
        StringBuilder texto = new StringBuilder();
        NodeList fragmentos = dato.getChildNodes();

        for (int k=0; k<fragmentos.getLength(); k++) {
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }

    //Obtenemos el inputStream
    private InputStream getInputStream() {
        try {
            return rssURL.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
