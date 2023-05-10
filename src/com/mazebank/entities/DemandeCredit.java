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


import java.util.Date;

public class DemandeCredit {
    private int id;
    private Credit creditId;
    private User userId;
    private int amount;
    private Date createdAt;
    private String note;
    private String status;
    private String cin1;
    private String cin2;

    public DemandeCredit() {
    }

    public DemandeCredit(int id, Credit credit, User user, int amount, Date createdAt, String note, String status, String cin1, String cin2) {
        this.id = id;
        this.creditId = credit;
        this.userId = user;
        this.amount = amount;
        this.createdAt = createdAt;
        this.note = note;
        this.status = status;
        this.cin1 = cin1;
        this.cin2 = cin2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCredit(Credit credit) {
        this.creditId = credit;
    }

    public Credit getCredit() {
        return creditId;
    }

    public void setUser(User user) {
        this.userId = user;
    }

    public User getUser() {
        return userId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCin1(String cin1) {
        this.cin1 = cin1;
    }

    public String getCin1() {
        return cin1;
    }

    public void setCin2(String cin2) {
        this.cin2 = cin2;
    }

    public String getCin2() {
        return cin2;
    }

    
}

