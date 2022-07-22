/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Candy;
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
            InsufficientFundsException, NoItemInventoryException {
        if(dao.buyCandy(candy.getCandyNumber()) == null){
            throw new NoItemInventoryException("ERROR: Could not buy candy. Candy does not exist"); // print user input
        }
        validateCandyData(candy);
        dao.buyCandy(candy.getCandyNumber());
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
        if(candy.getCandyNumber() == null || candy.getCandyNumber().trim().length() == 0) {
            throw new NoItemInventoryException("ERROR: "); // does not exist
        }
    }
    
}
