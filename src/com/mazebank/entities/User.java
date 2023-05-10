/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazebank.entities;

/**
 *
 * @author ASUS
 */

import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.util.Date;

public class User  {
    private int id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String numTel;

    public User() {
    }

    public User(int id, String nom, String prenom, Date dateNaissance, String numTel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getNumTel() {
        return numTel;
    }

    
}
