/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Campos implements Serializable{
    private static final long SerialVersioUID = 69111L;
    String tipo, nombre,llave;
    int longitud;
    public Campos() {
    }

    public Campos(String tipo, String nombre, int longitud, String llave) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.longitud = longitud;
        this.llave = llave;
    }

    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
