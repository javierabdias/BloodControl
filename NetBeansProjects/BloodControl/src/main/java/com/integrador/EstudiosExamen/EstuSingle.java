/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.EstudiosExamen;

/**
 *
 * @author abdias
 */
public class EstuSingle {

    private static String nombre;
    
    private EstuSingle(){}

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        EstuSingle.nombre = nombre;
    }
    
}
