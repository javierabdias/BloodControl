/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

/**
 *
 * @author abdias
 */
public class Lab {
    
    public String cedula, nombre, epePat, apeMat;

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEpePat() {
        return epePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEpePat(String epePat) {
        this.epePat = epePat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public Lab(String cedula, String nombre, String epePat, String apeMat) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.epePat = epePat;
        this.apeMat = apeMat;
    }

    
}
