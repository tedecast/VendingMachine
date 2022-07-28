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
    public void addMoney(BigDecimal money) throws VendingMachinePersistenceException {
        dao.addMoney(money);
    }
    
    @Override
    public BigDecimal getBalance(boolean finish) throws VendingMachinePersistenceException {
        BigDecimal balance = dao.getChangeBalance();

        if (finish == true) {
            auditDao.writeAuditEntry("$" + balance + " was returned.");
        }
        return balance;
    }
    
    // If the user selects an item that costs more than the amount the user put into the vending machine,
    // the program should display a message indicating insufficient funds 
    // and then redisplay the amount the user had put into the machine.
    @Override
    public Candy buyCandy(int candyNumber) throws VendingMachinePersistenceException,
        InsufficientFundsException, NoItemInventoryException {
        
        //Use a filter to filter to the single candy object being bought
        List<Candy> selectedCandyList = dao.getAllCandy().stream()
                .filter((sC) -> sC.getCandyNumber() == candyNumber)
                .collect(Collectors.toList());
        Candy selectedCandy = selectedCandyList.get(0);         
        BigDecimal balance = dao.getChangeBalance();

        if (balance.compareTo(selectedCandy.getCandyPrice()) == -1 ) {
            throw new InsufficientFundsException("ERROR: Insufficient Funds. "
                    + "\nYou only put in $" + balance 
                    + "\nPlease Add Money at the Main Menu."); // print user input;
        }
        
        if (selectedCandy.getCandyQuantity() <= 0) {
            throw new NoItemInventoryException(
                    "ERROR: No such Candy Number exists." +
                    "\nPlease try again at the Main Menu."); 

        }
        //Only do this if no errors get thrown
        Candy boughtCandy = dao.buyCandy(candyNumber);
        //candy.getCandyName() 
        auditDao.writeAuditEntry("CANDY " + boughtCandy.getCandyName() + " WAS PURCHASED.");
        return boughtCandy;
    }
    
    @Override
    public String getBalanceInCoins() throws VendingMachinePersistenceException{
        return dao.getBalanceInCoins();
    }
}
