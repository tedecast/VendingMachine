/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Candy;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao; 
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao,
            VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    // declare candy item (item number), and getOneCandy, get rid of it
    // dao get BigDecimal balance, get change balance
    // if balance candy cost 
    // vending Machine 
    // change up menu 
    // call, purchase item and item ID , return once purchased
    // aduit service
    
    @Override
    public List<Candy> getAllCandy() throws VendingMachinePersistenceException {
        return dao.getAllCandy();
    }
    
    @Override
    public Candy buyCandy(int candyNumber) throws VendingMachinePersistenceException,
        InsufficientFundsException, NoItemInventoryException {
        
        Candy candy = dao.buyCandy(candyNumber);
        BigDecimal balance = dao.getChangeBalance();
        
        if (balance.compareTo(candy.getCandyPrice()) == -1 ||
                balance.compareTo(BigDecimal.ZERO) == 0) {
            throw new InsufficientFundsException("Insufficient Funds. You only have $" + balance 
                    + "\nPlease add more money at the Main Menu."); // print user input;
        }
        
        if (candy.getCandyQuantity() == 0) {
            throw new NoItemInventoryException(
                    "\n                    ERROR:"
                    + "\nPlease choose an existing Candy's Number to purchase."); // print user input

        }
        //candy.getCandyName() 
        auditDao.writeAuditEntry("CANDY " + candy.getCandyName() + " PURCHASED.");
    }
    
    @Override
    public BigDecimal getBalance(boolean finish) throws VendingMachinePersistenceException {
        BigDecimal balance = dao.getChangeBalance();

        if (finish == true) {
            auditDao.writeAuditEntry("$" + balance + " was returned.");
        }
        return balance;
    }
    
}
