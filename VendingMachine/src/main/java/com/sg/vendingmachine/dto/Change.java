/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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
    public int[] makeChange(BigDecimal change) {
        
        while(BigDecimal.ZERO.compareTo(change) == -1) {
            
            if (change.compareTo(Coins.QUARTER.value) == 0) {
                change.subtract(Coins.QUARTER.value);
                quarters++;
                
            } else if (change.compareTo(Coins.DIME.value) == 0) {
                change.subtract(Coins.DIME.value);
                dimes++;
                
            } else if (change.compareTo(Coins.NICKEL.value) == 0) {
                change.subtract(Coins.NICKEL.value);
                nickels++;
                
            } else if (change.compareTo(Coins.PENNY.value) == 0) {
                change.subtract(Coins.PENNY.value);
                pennies++;
                
            } else {
                pennies++;
                break;
            }
            
        }
        int[] changeArr = {quarters, dimes, nickels, pennies};
        return changeArr;
    }
}
