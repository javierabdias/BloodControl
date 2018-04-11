package com.integrador.bloodcontrol;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alertas {
    
       public static void warning(String titulo, String encabezado, String texto){
       
           Alert alert= new Alert(AlertType.WARNING);
           alert.setTitle(titulo);
           alert.setHeaderText(encabezado);
           alert.setContentText(texto);
           alert.showAndWait();
           
       }
       
       public static void informacionEncabezado(String titulo, String encabezado, String texto){
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle(titulo);
           alert.setHeaderText(encabezado);
           alert.setContentText(texto);
           alert.showAndWait();
       }
       
       public static void informacion(String titulo, String texto){
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle(titulo);
           alert.setHeaderText(null);
           alert.setContentText(texto);
           alert.showAndWait();
       }
       
       public static void error (String titulo, String encabezado, String texto){
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle(titulo);
           alert.setHeaderText(encabezado);
           alert.setContentText(texto);
           alert.showAndWait();
       }
       
       public static void confirmacion(){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminación");
            alert.setHeaderText("Eliminar registro.");
            alert.setContentText("¿Está seguro de eliminar este registro?");

           Optional<ButtonType> result = alert.showAndWait();
           if (result.get() == ButtonType.OK){
            //Acciones de ok
           } else {
            //Acciones de cancel
            }
        }
    
}
