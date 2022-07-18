/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Teresa
 */
public class Candy {
    private String candyNumber;
    private String candyName;
    private BigDecimal candyPrice;
    private int candyQuantity;
    
    public Candy(String candyNumber) {
        this.candyNumber = candyNumber;
    }

    public String getCandyNumber() {
        return candyNumber;
    }

    public void setCandyNumber(String candyNumber) {
        this.candyNumber = candyNumber;
    }

    public String getCandyName() {
        return candyName;
    }

    public void setCandyName(String candyName) {
        this.candyName = candyName;
    }

    public BigDecimal getCandyPrice() {
        return candyPrice;
    }

    public void setCandyPrice(BigDecimal candyPrice) {
        this.candyPrice = candyPrice;
    }

    public int getCandyQuantity() {
        return candyQuantity;
    }

    public void setCandyQuantity(int candyQuantity) {
        this.candyQuantity = candyQuantity;
    }
    
    public int purchaseItem() {
        return candyQuantity--;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.candyNumber);
        hash = 29 * hash + Objects.hashCode(this.candyName);
        hash = 29 * hash + Objects.hashCode(this.candyPrice);
        hash = 29 * hash + this.candyQuantity;
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
        final Candy other = (Candy) obj;
        if (this.candyQuantity != other.candyQuantity) {
            return false;
        }
        if (!Objects.equals(this.candyNumber, other.candyNumber)) {
            return false;
        }
        if (!Objects.equals(this.candyName, other.candyName)) {
            return false;
        }
        if (!Objects.equals(this.candyPrice, other.candyPrice)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
