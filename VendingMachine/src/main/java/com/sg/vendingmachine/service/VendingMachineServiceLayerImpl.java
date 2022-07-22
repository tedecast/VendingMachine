/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Candy;
import com.sg.vendingmachine.dto.Change;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    private VendingMachineDao dao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao){
        this.dao = dao;
    }

    @Override
    public void buyCandy(Candy candy) throws VendingMachinePersistenceException, 
            NoItemInventoryException {
        if(dao.buyCandy(candy.getCandyNumber()) == null){
            throw new NoItemInventoryException("ERROR: Could not buy candy. Your choice does not exist."); // print user input
        }
        validateCandyData(candy);
        dao.buyCandy(candy.getCandyNumber());
    }
    
    @Override
    public void moneyIn(Change change) throws VendingMachinePersistenceException,
            InsufficientFundsException {
        if(change.getBalance() == null){
            throw new InsufficientFundsException("ERROR: " + change.getBalance() +
                                                " is not valid.");
        }
        validateFunds(change);
        change.getBalance();
    }

    @Override
    public List<Candy> getAllCandy() throws VendingMachinePersistenceException {
        return dao.getAllCandy();
    }

    @Override
    public Candy getOneCandy(String candy) throws VendingMachinePersistenceException {
        return dao.getOneCandy(candy);
    }
    
    private void validateCandyData(Candy candy) throws NoItemInventoryException {
        if(candy.getCandyNumber() == null || 
                candy.getCandyNumber().trim().length() == 0 ) {
            
            throw new NoItemInventoryException("ERROR: "); // does not exist
        }
    }
    
    private void validateFunds(Change userChange) throws InsufficientFundsException {
        if(userChange.getBalance() == null || 
            userChange.getBalance().toString().length() == 0) {
                throw new InsufficientFundsException("ERROR: Please enter a valid amount of money."); // enter user input
        }
    }

   
    
}
