/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Candy;
import com.sg.vendingmachine.dto.Change;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface VendingMachineDao {
    
    List<Candy> getAllCandy() throws VendingMachinePersistenceException;
    
    Candy buyCandy(int candyNumber) throws VendingMachinePersistenceException;
    
    BigDecimal getChangeBalance(Change balance) throws VendingMachinePersistenceException;    
    
    
    // (i.e. InsufficientFundsException)
    // (i.e. NoItemInventoryException)
}
