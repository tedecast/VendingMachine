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
    
    // Returns the current balance(userChange) from the Vending Machine
    public BigDecimal getChangeBalance() throws VendingMachinePersistenceException;   
    
    // This method creates a List with Candy and filters out the only CandyNumber (id)
    // that the user chooses to buy. 
    // Then uses the current balance(userChange) to purchase the Candy
    // subtracts 1 from the candy's quantity
    // only writes back to the Inventory File if the candy's quantity is greater than 0
    // returns the bought Candy
    public Candy buyCandy(int candyNumber) throws VendingMachinePersistenceException;
    
    // Returns the change from userChange in coins - quarters, dimes, nickels, pennies,
    // along with the total.
    public String getBalanceInCoins() throws VendingMachinePersistenceException;  
    
}
