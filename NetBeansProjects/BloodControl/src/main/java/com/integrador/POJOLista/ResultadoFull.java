/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

/**
 *
 * @author abdias
 */
public class ResultadoFull {
    
    String examen, estudio,referencia;
    Double min, max, resultado;

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public ResultadoFull(String examen, String estudio, Double min, Double max, Double resultado, String referencia) {
        this.examen = examen;
        this.estudio = estudio;
        this.referencia = referencia;
        this.min = min;
        this.max = max;
        this.resultado = resultado;
    }
    
    
}
