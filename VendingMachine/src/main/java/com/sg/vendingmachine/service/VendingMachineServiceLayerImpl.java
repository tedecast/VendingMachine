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
    public void buyCandy(int candyNumber, BigDecimal balance) throws VendingMachinePersistenceException,
            NoItemInventoryException, InsufficientFundsException {
        Candy candy = dao.buyCandy(candyNumber);
        balance = dao.getChangeBalance();

        if (candy.getCandyQuantity() == 0) {
            throw new NoItemInventoryException(
                    "\n       " + candy.getCandyName()
                    + "\n       is unavailable for purchase."
                    + "\nPlease choose a different Candy's Number to purchase. SERVICE"); // print user input

        }
        if (balance.compareTo(candy.getCandyPrice()) == -1) {
            throw new InsufficientFundsException("Insufficient Funds. You only have $" + balance 
                    + "\nPlease add more money at the Main Menu."); // print user input;
        }

    }


    @Override
    public List<Candy> getAllCandy() throws VendingMachinePersistenceException {
        return dao.getAllCandy();
    }
    
}
