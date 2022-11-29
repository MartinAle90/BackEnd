
package com.portfolio.mad.Dto;

import javax.validation.constraints.NotBlank;


public class dtoAcerca {
    @NotBlank
    private String titulacionA;
    @NotBlank
    private String descripcionA;
    
    //constructores

    public dtoAcerca() {
    }

    public dtoAcerca(String titulacionA, String descripcionA) {
        this.titulacionA = titulacionA;
        this.descripcionA = descripcionA;
    }
    
    //getter and setter

    public String getTitulacionA() {
        return titulacionA;
    }

    public void setTitulacionA(String titulacionA) {
        this.titulacionA = titulacionA;
    }

    public String getDescripcionA() {
        return descripcionA;
    }

    public void setDescripcionA(String descripcionA) {
        this.descripcionA = descripcionA;
    }
    



}
