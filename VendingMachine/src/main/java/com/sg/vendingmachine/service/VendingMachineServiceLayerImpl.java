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
import java.util.stream.Collectors;

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
    //change to addMoney here and DAO
    public void AddMoney(BigDecimal money) throws VendingMachinePersistenceException {
        dao.AddMoney(money);
    }
    
    @Override
    public Candy buyCandy(int candyNumber) throws VendingMachinePersistenceException,
        InsufficientFundsException, NoItemInventoryException {
        
        //Use a filter to filter to the single candy object being bought
        List<Candy> selectedCandyList = dao.getAllCandy().stream()
                .filter((sC) -> sC.getCandyNumber() == candyNumber)
                .collect(Collectors.toList());
        Candy selectedCandy = selectedCandyList.get(0);         
        BigDecimal balance = dao.getChangeBalance();
        System.out.println(balance + " - Balance");

        if (balance.compareTo(selectedCandy.getCandyPrice()) == -1 ) {
            throw new InsufficientFundsException("Insufficient Funds. You only have $" + balance 
                    + "\nPlease add more money at the Main Menu."); // print user input;
        }
        
        if (selectedCandy.getCandyQuantity() <= 0) {
            throw new NoItemInventoryException(
                    "\n                    ERROR:"
                    + "\nPlease choose an existing Candy's Number to purchase."); // print user input

        }
        //Only do this if no errors get thrown
        Candy boughtCandy = dao.buyCandy(candyNumber);
        //candy.getCandyName() 
        auditDao.writeAuditEntry("CANDY " + boughtCandy.getCandyName() + " PURCHASED.");
        return boughtCandy;
    }
    
    @Override
    public BigDecimal getBalance(boolean finish) throws VendingMachinePersistenceException {
        BigDecimal balance = dao.getChangeBalance();

        if (finish == true) {
            auditDao.writeAuditEntry("$" + balance + " was returned.");
        }
        return balance;
    }
    
    @Override
    public String getBalanceInCoins() throws VendingMachinePersistenceException{
        return dao.getBalanceInCoins();
    }
}
