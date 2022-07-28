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
    
    public List<Candy> getAllCandy() throws VendingMachinePersistenceException;
    
    public void addMoney(BigDecimal money) throws VendingMachinePersistenceException;
    
    public BigDecimal getChangeBalance() throws VendingMachinePersistenceException;   
    
    public Candy buyCandy(int candyNumber) throws VendingMachinePersistenceException;
    
    public String getBalanceInCoins() throws VendingMachinePersistenceException;  
    
}
