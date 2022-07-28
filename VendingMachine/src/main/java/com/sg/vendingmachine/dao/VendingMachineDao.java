/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Candy;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface VendingMachineDao {
    // Returns a List of all the Candy in the inventory
    public List<Candy> getAllCandy() throws VendingMachinePersistenceException;
    
    // Adds money in BigDecimal as long as the money being added is greater than 0 
    public void addMoney(BigDecimal money) throws VendingMachinePersistenceException;
    
    // Returns the current balance in the Vending Machine
    public BigDecimal getChangeBalance() throws VendingMachinePersistenceException;   
    
    // This method uses the money that hass been added with the addMoney method
    public Candy buyCandy(int candyNumber) throws VendingMachinePersistenceException;
    
    // Returns the change in coins - quarters, dimes, nickels, pennies, to the user
    // along with the total.
    public String getBalanceInCoins() throws VendingMachinePersistenceException;  
    
}
