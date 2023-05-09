/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Objects;

/**
 *
 * @author achra
 */
public class Reclamation {
     private int id ;
//     private int id_client;alalalal
    private String description,etat;

    public Reclamation(int id, int id_client, String description, String etat) {
        this.id = id;
//        this.id_client = id_client;
        this.description = description;
        this.etat = etat;
    }

    public Reclamation(int id_client, String description, String etat) {
//        this.id_client = id_client;
        this.description = description;
        this.etat = etat;
    }

    public Reclamation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getid_client() {
//        return id_client;
//    }

//    public void setid_client(int id_client) {
//        this.id_client = id_client;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
//        if (!Objects.equals(this.id_client, other.id_client)) {
//            return false;
//        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "reclamations{" + "id=" + id + ", description=" + description + ", etat=" + etat + '}';
//        return "reclamations{" + "id=" + id + ", id_client=" + id_client + ", description=" + description + ", etat=" + etat + '}';

    }
    
    
    
    
}
