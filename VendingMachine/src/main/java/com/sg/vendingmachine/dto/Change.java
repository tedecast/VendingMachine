/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Teresa
 */
public class Change {

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
    
    
    public int[] makeChange(float change) {
        
        while(change > 0) {
            
            if (change >= 0.25) {
                change -= 0.25;
                quarters++;
            } else if (change >= 0.10) {
                change -= 0.10;
                dimes++;
            } else if (change >= 0.05) {
                change -= 0.05;
                nickels++;
            } else if (change >= 0.01) {
                change -= 0.01;
                pennies++;
            } 
            
        }
        int[] changeArr = {quarters, dimes, nickels, pennies};
        return changeArr ;
    }
}
