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
// Use enums to represent the values of different coins.
public enum Coins {
    QUARTER(new BigDecimal("0.25")),
    DIME(new BigDecimal("0.10")),
    NICKEL(new BigDecimal("0.05")),
    PENNY(new BigDecimal("0.01"));
    
    public final BigDecimal value;
    private Coins(BigDecimal value) {
        this.value = value;
    }
    public BigDecimal getValue() {
        return value;
        
    }
}
