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
public class Livreur {
    private int ID_livreur;
     private String nom;
     private String mail;
     private int telephone;

    public Livreur(String nom, String mail, int telephone) {
        this.nom = nom;
        this.mail = mail;
        this.telephone = telephone;
    }

     
    public Livreur(int ID_livreur, String nom, String mail, int telephone) {
        this.ID_livreur = ID_livreur;
        this.nom = nom;
        this.mail = mail;
        this.telephone = telephone;
    }

    public Livreur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getID_livreur() {
        return ID_livreur;
    }

    public void setID_livreur(int ID_livreur) {
        this.ID_livreur = ID_livreur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Livreur{" + "ID_livreur=" + ID_livreur + ", nom=" + nom + ", mail=" + mail + ", telephone=" + telephone + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.ID_livreur;
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
        final Livreur other = (Livreur) obj;
        if (this.ID_livreur != other.ID_livreur) {
            return false;
        }
        return true;
    }
}
