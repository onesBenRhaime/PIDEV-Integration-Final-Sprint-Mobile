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



public class CategoryCredit  {
    private int id;
    private String name;
    private String description;

    public CategoryCredit() {
    }

    public CategoryCredit(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }




   
}
