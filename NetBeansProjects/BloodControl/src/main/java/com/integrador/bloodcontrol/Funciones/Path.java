package com.integrador.bloodcontrol.Funciones;


public class Path {
    
     private static String  path = System.clearProperty("user.home");
     
     private Path(){}

    public static String getPath() {
        return path;
    }
     
     
    
}
