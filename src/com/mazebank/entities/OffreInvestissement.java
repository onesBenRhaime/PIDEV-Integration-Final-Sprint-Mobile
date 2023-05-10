package com.mazebank.entities;

import java.util.Date;
import com.mazebank.utils.*;

public class OffreInvestissement {
    
    private int id;
     private int minBudget;
    
    public OffreInvestissement() {}

    public OffreInvestissement(int id, int minBudget) {
        this.id = id;
        this.minBudget = minBudget;
    }

    public OffreInvestissement(int minBudget) {
        this.minBudget = minBudget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(int minBudget) {
        this.minBudget = minBudget;
    }
    
    
    
}