/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Teresa
 */
public class Change {
    
    Candy candy;
    
    private int dollars, quarters, dimes, nickels, pennies;
    
    public void makeChange(Candy candy, BigDecimal deposit) {
        
        BigDecimal one = new BigDecimal("1");
        BigDecimal five = new BigDecimal("5");
        BigDecimal ten = new BigDecimal("10");
        BigDecimal twentyFive = new BigDecimal("25");
        BigDecimal oneHundred = new BigDecimal("100");
        
        BigDecimal price = candy.getCandyPrice();
        
        BigDecimal userChange = deposit.subtract(price);
        
        userChange = userChange.multiply(oneHundred);
        
        dollars = 0;
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
        
        // change to enum?
        while (userChange.floatValue() > 0){
            if (userChange.floatValue() >= 100){
                userChange = userChange.subtract(oneHundred);
                dollars++;
            } else if (userChange.floatValue() >= 25) {
                userChange = userChange.subtract(twentyFive);
                quarters++;
            } else if (userChange.floatValue() >= 10) {
                userChange = userChange.subtract(ten);
                dimes++;
            } else if (userChange.floatValue() >= 5) {
                userChange = userChange.subtract(five);
                nickels++;
            } else if (userChange.floatValue() >= 1) {
                userChange = userChange.subtract(one);
                pennies++;
            }
        }
    }

    public int getDollars() {
        return dollars;
    }
    
    public int getQuarters() {
        return quarters;
    }
    
    public int getDimes() {
        return dimes;
    }
    
    public int getNickels() {
        return nickels;
    }
    
    public int getPennies() {
        return pennies;
    }
    
}

