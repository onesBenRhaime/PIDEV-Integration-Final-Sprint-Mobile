package com.mazebank.entities;

import java.util.Date;

public class Transaction {
     private int id;
       private String requestTo ,requestFrom,montant,typeTransaction;

    public Transaction(int id, String requestTo, String requestFrom, String montant) {
        this.id = id;
        
        this.requestTo = requestTo;
        this.requestFrom = requestFrom;
        this.montant = montant;
        //this.typeTransaction = typeTransaction;
    }
    public Transaction(int id){this.id = id; }
    public Transaction(){}

    public Transaction(int id, String requestTo, String requestFrom, String montant, String typeTransaction) {
        this.id=id;
        this.requestTo = requestTo;
        this.requestFrom = requestFrom;
        this.montant = montant;
        this.typeTransaction = typeTransaction;
    }
     public Transaction(String requestTo, String requestFrom, String montant, String typeTransaction) {
     
        this.requestTo = requestTo;
        this.requestFrom = requestFrom;
        this.montant = montant;
        this.typeTransaction = typeTransaction;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + " requestTo=" + requestTo + ", requestFrom=" + requestFrom + ", montant=" + montant + ", typeTransaction=" + typeTransaction + '}';
    }

    
    
    
    




    

   
    
    
    
}
