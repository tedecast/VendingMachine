/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Candy;
import com.sg.vendingmachine.dto.Change;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface VendingMachineServiceLayer {
    
    public List<Candy> getAllCandy() throws VendingMachinePersistenceException;
    
    public Candy buyCandy(int candyNumber) throws VendingMachinePersistenceException,
            NoItemInventoryException, InsufficientFundsException;
    
    public BigDecimal getBalance(boolean finish) throws VendingMachinePersistenceException;
    
    
}
