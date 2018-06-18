
package com.integrador.POJOLista;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author abdias
 */
public class CitaExamen {

    private StringProperty nombre;
    private Double precio;
    private IntegerProperty id;
   
    
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer idUsuario) {
        this.id = new SimpleIntegerProperty(idUsuario);
    }
    
    
    public String getNombre() {
        return nombre.get();
    }
    
    public Double getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public CitaExamen(Integer id, String nombre, Double precio) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = precio;
    }
    
    public CitaExamen(Integer id, String nombre, Double precio, String correo) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = precio;
    }
    
    

}
