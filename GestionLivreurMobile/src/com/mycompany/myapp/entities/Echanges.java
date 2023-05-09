/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author amine
 */
public class Echanges {
    private int id_echange;
    private int produit_echange;
    private int produit_offert;
     private String statut;
     private String commentaire;

    public Echanges(int produit_echange, int produit_offert, String statut, String commentaire) {
        this.produit_echange = produit_echange;
        this.produit_offert = produit_offert;
        this.statut = statut;
        this.commentaire = commentaire;
    }

    public Echanges(int id_echange, int produit_echange, int produit_offert, String statut, String commentaire) {
        this.id_echange = id_echange;
        this.produit_echange = produit_echange;
        this.produit_offert = produit_offert;
        this.statut = statut;
        this.commentaire = commentaire;
    }

    public Echanges() {
    }

    public int getId_echange() {
        return id_echange;
    }

    public void setId_echange(int id_echange) {
        this.id_echange = id_echange;
    }

    public int getProduit_echange() {
        return produit_echange;
    }

    public void setProduit_echange(int produit_echange) {
        this.produit_echange = produit_echange;
    }

    public int getProduit_offert() {
        return produit_offert;
    }

    public void setProduit_offert(int produit_offert) {
        this.produit_offert = produit_offert;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Echanges{" + "id_echange=" + id_echange + ", produit_echange=" + produit_echange + ", produit_offert=" + produit_offert + ", statut=" + statut + ", commentaire=" + commentaire + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id_echange;
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
        final Echanges other = (Echanges) obj;
        if (this.id_echange != other.id_echange) {
            return false;
        }
        return true;
    }
     
}
