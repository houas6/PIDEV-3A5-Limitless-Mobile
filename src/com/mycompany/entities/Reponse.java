/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author achra
 */
public class Reponse {
    private int id;
    private String contenuRep;
    private int idReclamation;

    // Constructor
    public Reponse(int idRep, String contenuRep, int idReclamation) {
        this.id = idRep;
        this.contenuRep = contenuRep;
        this.idReclamation = idReclamation;
    }

    // Empty constructor
    public Reponse() {}

    public int getIdRep() {
        return id;
    }

    public void setIdRep(int idRep) {
        this.id = idRep;
    }



    public String getContenuRep() {
        return contenuRep;
    }

    public void setContenuRep(String contenuRep) {
        this.contenuRep = contenuRep;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }
    
}
