/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Funciones;

import java.util.Calendar;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

/**
 *
 * @author abdias
 */

public class Reloj extends Task <Void>{

    Label label;
  
    Calendar calendario = Calendar.getInstance();
    int horas = calendario.get(Calendar.HOUR);
    int minutos = calendario.get(Calendar.MINUTE);
    int segundos = calendario.get(Calendar.SECOND);
    String min = "", seg = "", hora = "";

    public Reloj(Label label) {
        this.label = label;
    }

    @Override
    protected Void call() throws Exception {
        
        while (true){
                                        
                if(horas==0){
                horas=12;
                }

                if (segundos > 59) {
                    segundos = 0;
                    minutos++;
                }

                if (minutos > 59) {
                    minutos = 0;
                    horas++;
                }

                if (segundos < 10) {
                    seg = "0" + segundos;
                } else {
                    seg = Integer.toString(segundos);
                }

                if (minutos < 10) {
                    min = "0" + minutos;
                } else {
                    min = Integer.toString(minutos);
                }

                if (horas < 10) {
                    hora = "0" + horas;
                } else {
                    hora = Integer.toString(horas);
                }              
            
            Platform.runLater(() -> {
                label.setText(hora + ":" + min + ":" + seg);
                System.gc();
            });
            
             segundos++;
             Thread.sleep(1000);
        }
    }
    
}
