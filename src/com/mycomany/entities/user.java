/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;



/**
 *
 * @author hasse
 */
public class user {
    
    private int id_user;
    private String numero;
    private String nom;
    private String prenom;
    private String cin;
    private String role;
    private String password;
    private String mail;
    private String adresse;

    public user(int id_user, String numero, String nom, String prenom, String cin, String role, String password, String mail, String adresse) {
        this.id_user = id_user;
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.role = role;
        this.password = password;
        this.mail = mail;
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public user() {
    }

    public user(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public user(String numero, String password, String mail) {
        this.numero = numero;
        this.password = password;
        this.mail = mail;
    }

    public user(int id_user, String password, String mail) {
        this.id_user = id_user;
        this.password = password;
        this.mail = mail;
    }

    public user(int id_user, String numero, String password, String mail) {
        this.id_user = id_user;
        this.numero = numero;
        this.password = password;
        this.mail = mail;
    }

    
    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return numero;
    }

    public void setUsername(String numero) {
        this.numero = numero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

   

    @Override
    public String toString() {
        return "user{" + "id_user=" + id_user + ", numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", role=" + role + ", password=" + password + ", mail=" + mail + '}';
    }

   

}
