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

    @Override
    public void buyCandy(String candyNumber) throws VendingMachinePersistenceException, 
            NoItemInventoryException {
        Candy candy = dao.getOneCandy(candyNumber);
        //List<Candy> candyList = dao.getAllCandy();
        while(candy.getCandyQuantity() == 0){
            throw new NoItemInventoryException("Sorry, we're out of stock of:" +
                    "\n       " + candy.getCandyName() +
                    "\nPlease choose a different Candy's Number to purchase. SERVICE"); // print user input
            
        }
        
//        validateCandyData(candy);
        //dao.buyCandy(candy.getCandyNumber());
    }
    
//    @Override
//    public void moneyIn(Change change) throws VendingMachinePersistenceException,
//            InsufficientFundsException {
//        if(change.getBalance() == null){
//            throw new InsufficientFundsException("ERROR: " + change.getBalance() +
//                                                " is not valid.");
//        }
//        validateFunds(change);
//        change.getBalance();
//    }

    @Override
    public List<Candy> getAllCandy() throws VendingMachinePersistenceException {
        return dao.getAllCandy();
    }

    @Override
    public Candy getOneCandy(String candy) throws VendingMachinePersistenceException {
        return dao.getOneCandy(candy);
    }
    
//   private void validateCandyData(Candy candy) throws NoItemInventoryException {
//        if(candy.getCandyNumber() == null || 
//                //candy.getCandyNumber().trim().length() == 0 ||
//                candy.getCandyNumber().isEmpty() ||
//                candy.getCandyNumber() != candy.getCandyNumber()) {
//            
//            throw new NoItemInventoryException("ERROR: "); // does not exist
//        }
//    }
    
//    private void validateFunds(Change userChange) throws InsufficientFundsException {
//        if(userChange.getBalance() == null || 
//            userChange.getBalance().toString().length() == 0) {
//                throw new InsufficientFundsException("ERROR: Please enter a valid amount of money."); // enter user input
//        }
//    }

   
    
}
