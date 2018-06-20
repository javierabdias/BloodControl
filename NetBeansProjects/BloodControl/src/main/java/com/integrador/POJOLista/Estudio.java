/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author abdias
 */
public class Estudio {
    
    private IntegerProperty id;
    private StringProperty nombre;
    private DoubleProperty min;
    private DoubleProperty max;
    private StringProperty examen;
    
    public String getNombre() {
        return nombre.get();
    }
    
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty(id);
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
    
    public Estudio (Integer id, String nombre, Double min, Double max,String examen ) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.min = new SimpleDoubleProperty(min);
        this.max = new SimpleDoubleProperty(max);
        this.examen = new SimpleStringProperty(examen);
    }
    
    
}
