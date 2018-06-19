/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author abdias
 */
public class Estudio {
    
    private StringProperty nombre;
    private DoubleProperty min;
    private DoubleProperty max;
    private StringProperty examen;
    
    public String getNombre() {
        return nombre.get();
    }
    
    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }
    
    public Double getMin() {
        return min.get();
    }
    
    public void setMin (Double min){
        this.min = new SimpleDoubleProperty(min);
    }
    
    public Double getMax() {
        return max.get();
    }
    
    public void setMax (Double max){
        this.max = new SimpleDoubleProperty(max);
    }
    
    public String getExamen() {
        return examen.get();
    }
    
    public void setExamen (String examen) {
        this.examen = new SimpleStringProperty(examen);
    }
    
    public Estudio (String nombre, Double min, Double max,String examen ) {
        this.nombre = new SimpleStringProperty(nombre);
        this.min = new SimpleDoubleProperty(min);
        this.max = new SimpleDoubleProperty(max);
        this.examen = new SimpleStringProperty(examen);
    }
    
    
}
