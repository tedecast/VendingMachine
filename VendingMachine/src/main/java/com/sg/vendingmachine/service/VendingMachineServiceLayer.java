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
// possibly changing to String candyNumber
    public void buyCandy(String candyNumber) throws VendingMachinePersistenceException,
            NoItemInventoryException;

    public List<Candy> getAllCandy() throws VendingMachinePersistenceException;
    
    public Candy getOneCandy(String candyNumber) throws VendingMachinePersistenceException;
    
    //public void moneyIn(Change money) throws VendingMachinePersistenceException,
                        //InsufficientFundsException;
    
}
