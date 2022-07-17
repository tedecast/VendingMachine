/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Teresa
 */
public class Change {

    BigDecimal quarter_value = new BigDecimal("0.25").setScale(2, RoundingMode.HALF_UP);
    BigDecimal dime_value = new BigDecimal("0.10").setScale(2, RoundingMode.HALF_UP);
    BigDecimal nickel_value = new BigDecimal("0.05").setScale(2, RoundingMode.HALF_UP);
    BigDecimal penny_value = new BigDecimal("0.01").setScale(2, RoundingMode.HALF_UP);
    
    int quarters;
    int dimes;
    int nickels;
    int pennies;
    
    public void makeChange(Candy candy, BigDecimal cashDeposited) {
        
    }}
