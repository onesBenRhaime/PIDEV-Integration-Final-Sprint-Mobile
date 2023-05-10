package com.mazebank.entities;

import java.util.Date;
import com.mazebank.utils.*;

public class CarteBancaire {
     private int id;
       private String identifier,email,description,cinS1;

    public CarteBancaire(int id, String identifier, String email, String description) {
        this.id = id;
        
        this.identifier = identifier;
        this.email = email;
        this.description = description;
    }

    public CarteBancaire(){}

     public CarteBancaire(int id, String identifier, String email, String description, String cinS1) {
        this.id=id;
        this.identifier = identifier;
        this.email = email;
        this.description = description;
        this.cinS1 = cinS1;
    
    }
     public CarteBancaire(String identifier, String email, String description, String cinS1) {
     
        this.identifier = identifier;
        this.email = email;
        this.description = description;
        this.cinS1 = cinS1;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCinS1() {
        return cinS1;
    }

    public void setCinS1(String cinS1) {
        this.cinS1 = cinS1;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", identifier=" + identifier + ", email=" + email + ", description=" + description + ", cinS1=" + cinS1 + '}';
    }

   
    


    
    
    
    




    

   
    
    
    
}
