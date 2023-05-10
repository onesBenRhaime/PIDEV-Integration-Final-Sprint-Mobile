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



public class Credit  {
    private int id;
    private CategoryCredit creditCategory;
    private int minAmount;
    private int maxAmount;
    private int withdrawMonthly;
    private int months;
    private int loanRate;

    public Credit() {
    }

    public Credit(int id, CategoryCredit category, int minAmount, int maxAmount, int withdrawMonthly, int months, int loanRate) {
        this.id = id;
        this.creditCategory = category;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.withdrawMonthly = withdrawMonthly;
        this.months = months;
        this.loanRate = loanRate;
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCategory(CategoryCredit category) {
        this.creditCategory = category;
    }

    public CategoryCredit getCategory() {
        return creditCategory;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setWithdrawMonthly(int withdrawMonthly) {
        this.withdrawMonthly = withdrawMonthly;
    }

    public int getWithdrawMonthly() {
        return withdrawMonthly;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getMonths() {
        return months;
    }

    public void setLoanRate(int loanRate) {
        this.loanRate = loanRate;
    }

    public int getLoanRate() {
        return loanRate;
    }

    @Override
    public String toString() {
        return "Credit{" + "id=" + id + ", creditCategory=" + creditCategory + ", minAmount=" + minAmount + ", maxAmount=" + maxAmount + ", withdrawMonthly=" + withdrawMonthly + ", months=" + months + ", loanRate=" + loanRate + '}';
    }

    
}
