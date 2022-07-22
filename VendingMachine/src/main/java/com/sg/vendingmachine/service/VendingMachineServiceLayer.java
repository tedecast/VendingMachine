/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Candy;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface VendingMachineServiceLayer {

    public Candy buyCandy(String candyNumber) throws VendingMachinePersistenceException,
            InsufficientFundsException,
            NoItemInventoryException;

    public List<Candy> getAllCandy() throws VendingMachinePersistenceException;
    
    public Candy getOneCandy(String candyNumber) throws VendingMachinePersistenceException;
    
}
