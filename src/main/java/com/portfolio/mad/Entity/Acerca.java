
package com.portfolio.mad.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Acerca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulacionA;
    private String descripcionA;
    
    //contructor en blanco y otr sin id
    public Acerca() {
    }

    public Acerca(String titulacionA, String descripcionA) {
        this.titulacionA = titulacionA;
        this.descripcionA = descripcionA;
    }
    
    //getter and setter (all)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
