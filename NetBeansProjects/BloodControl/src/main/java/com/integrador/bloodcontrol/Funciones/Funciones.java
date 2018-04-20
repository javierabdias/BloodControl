package com.integrador.bloodcontrol.Funciones;

import com.integrador.bloodcontrol.MainApp;
import com.integrador.persistence.EManagerFactory;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author abdias
 */

//  **CLASE ABSTRACTA PARA MÉTODOS QUE SE UTILIZAN EN TODO EL PROGRAMA**

public abstract class Funciones {
    
    // Método para crear nuevas ventanas
    public void crearVentanas(String ruta, String nomVentana) throws IOException {
        Parent root = (Parent) FXMLLoader.load(MainApp.class.getResource(ruta));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(nomVentana);
        stage.initModality(Modality.APPLICATION_MODAL);   
        stage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/MainScene/logo8x8-255.png")));
        valCerrar(stage);
        stage.showAndWait();  
        stage=null;
        System.gc();
    }
    
    public void crearVentanas2(String ruta, String nomVentana) throws IOException {
        Parent root = (Parent) FXMLLoader.load(MainApp.class.getResource(ruta));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(nomVentana);
        stage.initModality(Modality.APPLICATION_MODAL);   
        stage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/MainScene/logo8x8-255.png")));
        stage.showAndWait();  
        stage=null;
        System.gc();
    }
    
    //Método para cerrar la coneción con la base de datos
     public void valCerrar(Stage sta){
        sta.setOnCloseRequest((WindowEvent event) -> {
            EManagerFactory.getEntityManagerFactory().close();
        });
     }
         
      //Validar solo numeros
      public void TextFieldNumeros(TextField tf){
       tf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           if (!newValue.matches("[\\d*]")) {
               tf.setText(newValue.replaceAll("[^\\d]", ""));
           }
       });
    }
      
      public void TextFieldDouble(TextField tf){
       tf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           if (!newValue.matches("[\\d*.]")) {
                tf.setText(newValue.replaceAll("[^\\d.]", ""));
            }
       });
    }
       
          
      //Validar solo letras
      public void TextFieldLetras(JFXTextField tf){
        tf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.matches("[A-Za-z´ ]")){
                tf.setText(newValue.replaceAll("[^A-Za-z áéíóú]", ""));
            }
        });
      }
      
        //Limitar la cantidad de caracteres de un TextField
  public void setTextFieldLimit(TextField tf, int Limit){
     tf.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
         if (newValue.intValue() > oldValue.intValue()) {
             // Check if the new character is greater than LIMIT
             if (tf.getText().length() >= Limit) {
                 // if it's 11th character then just setText to previous one
                 tf.setText(tf.getText().substring(0,Limit));
             }
         }
     });   
  }

}
