/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

import com.integrador.bloodcontrol.Funciones.Funciones;
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
public class Resul extends Funciones{
    
    private IntegerProperty id; 
    private StringProperty examen;
    private StringProperty estudio;
    private DoubleProperty resultado;
    
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty(id);
    }
    
    public Double getResultado() {
        return resultado.get();
    }

    public void setResultado(Double resultado) {
        this.resultado = new SimpleDoubleProperty(resultado);
    }
    
    public String getExamen() {
        return examen.get();
    }
    
   public void setExamen(String examen) {
        this.examen= new SimpleStringProperty(examen);
    }
    
   public String getEstudio() {
        return estudio.get();
    }
    
   public void setEstudio(String estudio) {
        this.estudio= new SimpleStringProperty(estudio);
    }
   
    public Resul (Integer id, String examen, String estudios) {
        this.id = new SimpleIntegerProperty(id); 
        this.examen = new SimpleStringProperty(examen);
        this.estudio = new SimpleStringProperty(estudios);
    }
    
    public Resul (Integer id, String examen, String estudios, Double resultado) {
        this.id = new SimpleIntegerProperty(id); 
        this.examen = new SimpleStringProperty(examen);
        this.estudio = new SimpleStringProperty(estudios);
        this.resultado= new SimpleDoubleProperty(resultado);
    }
    
    public Resul (Integer id, String estudios) {
        this.id = new SimpleIntegerProperty(id); 
        this.estudio = new SimpleStringProperty(estudios);
    }
   
}
