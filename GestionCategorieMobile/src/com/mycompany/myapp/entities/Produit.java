/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Objects;

/**
 *
 * @author USER
 */
public class Produit {
    int id_produit;
    int id_user;
    int idcategorie;
    String nom_produit;
    float prix;
    String description;
    String image;

    public Produit() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id_produit;
        hash = 59 * hash + this.id_user;
        hash = 59 * hash + this.idcategorie;
        hash = 59 * hash + Objects.hashCode(this.nom_produit);
        hash = 59 * hash + Float.floatToIntBits(this.prix);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.image);
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
        final Produit other = (Produit) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.idcategorie != other.idcategorie) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.nom_produit, other.nom_produit)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", id_user=" + id_user + ", idcategorie=" + idcategorie + ", nom_produit=" + nom_produit + ", prix=" + prix + ", description=" + description + ", image=" + image + '}';
    }

    public Produit(int id_user, int idcategorie, String nom_produit, float prix, String description, String image) {
        this.id_user = id_user;
        this.idcategorie = idcategorie;
        this.nom_produit = nom_produit;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    public Produit(int id_produit, int id_user, int idcategorie, String nom_produit, float prix, String description, String image) {
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.idcategorie = idcategorie;
        this.nom_produit = nom_produit;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
