/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Funciones;

/**
 *
 * @author abdias
 */
public class Usuarios {
    
   private static int id;
   private static String tipo;
   
   private Usuarios(){}

    public static int getId() {
        return id;
    }

    public static String getTipo() {
        return tipo;
    }

    public static void setId(int id) {
        Usuarios.id = id;
    }

    public static void setTipo(String tipo) {
        Usuarios.tipo = tipo;
    }
   
   
    
}
