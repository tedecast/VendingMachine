/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 * @author Teresa
 */
public class Change {
    // To make change, create a Change class that takes the amount of change due to the user (in pennies) 
    // and then calculates the number of quarters, dimes, nickels, and pennies due back to the user. 
    // This class should have accessors for each of the coin types.
    
    Candy candy;

    private int quarters, dimes, nickels, pennies;
    private BigDecimal balance;

    public Change(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
        //divideAndRemainder returns two-element array ([0]-result [1]-remainder)
        int numCoins = 0;
        int remainder = 1;
        BigDecimal[] quartersArr = balance.divideAndRemainder(Coins.QUARTER.value);
        BigDecimal[] dimesArr = quartersArr[remainder].divideAndRemainder(Coins.DIME.value);
        BigDecimal[] nickelsArr = dimesArr[remainder].divideAndRemainder(Coins.NICKEL.value);
        BigDecimal[] penniesArr = nickelsArr[remainder].divideAndRemainder(Coins.PENNY.value);
        this.quarters = quartersArr[numCoins].intValue();
        this.dimes = dimesArr[numCoins].intValue();
        this.nickels = nickelsArr[numCoins].intValue();
        this.pennies = penniesArr[numCoins].intValue();
    }
    
    // You must use BigDecimal for all monetary calculations where applicable.
    public void addChange(BigDecimal change) {
        this.balance = this.balance.add(change).setScale(2, RoundingMode.HALF_UP);
    }
    public void makePurchase(BigDecimal price) {
        this.balance = this.balance.subtract(price).setScale(2, RoundingMode.HALF_UP);
    }
    public BigDecimal getBalance() {
        return balance.setScale(2, RoundingMode.HALF_UP);
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
    }
    public int getQuarters() {
        return quarters;
    }
    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }
    public int getDimes() {
        return dimes;
    }
    public void setDimes(int dimes) {
        this.dimes = dimes;
    }
    public int getNickels() {
        return nickels;
    }
    public void setNickels(int nickels) {
        this.nickels = nickels;
    }
    public int getPennies() {
        return pennies;
    }
    public void setPennies(int pennies) {
        this.pennies = pennies;
    }
    @Override
    public String toString() {
        return  "          " + quarters + " Quarters" +
                "\n          " + dimes + " Dimes" +
                "\n          " + nickels + " Nickels" +
                "\n          " + pennies + " Pennies" +
                "\n         Total: $" + balance;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.quarters;
        hash = 29 * hash + this.dimes;
        hash = 29 * hash + this.nickels;
        hash = 29 * hash + this.pennies;
        hash = 29 * hash + Objects.hashCode(this.balance);
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
        final Change other = (Change) obj;
        if (this.quarters != other.quarters) {
            return false;
        }
        if (this.dimes != other.dimes) {
            return false;
        }
        if (this.nickels != other.nickels) {
            return false;
        }
        if (this.pennies != other.pennies) {
            return false;
        }
        if (!Objects.equals(this.balance, other.balance)) {
            return false;
        }
        return true;
    }
    
}
