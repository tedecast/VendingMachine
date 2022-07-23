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
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    private VendingMachineDao dao;
    //private VendingMachineView view;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao) { //, VendingMachineView view){
        this.dao = dao;
        //this.view = view;
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
    public void buyCandy(int candyNumber) throws VendingMachinePersistenceException,
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
                    "\n       " + candy.getCandyName()
                    + "\n       is unavailable for purchase."
                    + "\nPlease choose a different Candy's Number to purchase. SERVICE"); // print user input

        }
//        if (balance.compareTo(candy.getCandyPrice()) == -1 ||
//                balance.compareTo(BigDecimal.ZERO) == 0) {
//            throw new InsufficientFundsException("Insufficient Funds. You only have $" + balance 
//                    + "\nPlease add more money at the Main Menu."); // print user input;
//        }

    }
    
    @Override
    public BigDecimal getBalance(boolean finish) throws VendingMachinePersistenceException {
        BigDecimal balance = dao.getChangeBalance();

//       if (finish == true) {
//            auditDao.writeAuditEntry("$" + balance + " Was Returned");
//        }
        return balance;
    }
    
    @Override
    public BigDecimal noBalance() throws VendingMachinePersistenceException,
            NoMoneyException {
        BigDecimal userMoney = dao.getChangeBalance();
        Change balance = new Change(userMoney);
        if (userMoney.compareTo(BigDecimal.ZERO) == 0) {
            throw new NoMoneyException (
            "      Your balance is $" + balance
            + "\nPlease Add Money at the Main Menu.");  
        }
        return userMoney;
        
    }
    
//    @Override
//    public BigDecimal mustAddMoney() throws VendingMachinePersistenceException,
//            NoMoneyException {
//        BigDecimal moneyAdded = dao.getChangeBalance();
//        if (moneyAdded.compareTo(BigDecimal.ZERO) == 0) {
//            throw new NoMoneyException ( "$" +
//            moneyAdded + " is not valid.");  
//        }
//        return moneyAdded;
//    }
}
