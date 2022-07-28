/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Candy;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Teresa
 */
public class VendingMachineController {
    
    private VendingMachineServiceLayer service;
    private VendingMachineView view; 
    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    
    // The program should display all of the items and their respective prices when the program starts, along with an option to exit the program.
    public void run() {
       
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing){
                view.displayCandyBanner();
                displayCandySelection();

                menuSelection = getMenuSelection();
                
                switch (menuSelection) {
                    case 1: 
                        view.displayCandyBanner();
                        displayCandySelection();
                        view.getHitEnter();
                        break;
                    case 2:
                        addMoney();
                        break;
                    case 3:
                        displayBalance();
                        view.getHitEnter();
                        break;
                    case 4:
                        buyCandy();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                       unknownCommand();
                }
            }
        exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void displayCandySelection() throws VendingMachinePersistenceException {
        //view.displayCandyBanner();
        view.displaySelectionBanner();
        List<Candy> filteredCandyList = service.getAllCandy().stream()
               .filter((c) -> c.getCandyQuantity() > 0)
               .collect(Collectors.toList());
        view.displayCandyList(filteredCandyList);
    }
    
    // The user must put in some amount of money before an item can be selected.
    private void addMoney() throws VendingMachinePersistenceException {
        view.displayAddMoneyBanner();
        BigDecimal userMoney = view.displayRequestUserMoney();
        service.addMoney(userMoney);
        view.addedMoneySuccessBanner(userMoney);
        view.currentBalance(service.getBalance(false));
        view.getHitEnter();
    }
    
    private void displayBalance() throws VendingMachinePersistenceException {
        view.displayBalanceBanner();
        view.currentBalance(service.getBalance(false)); //Should this be true?
    }
   
    // If the user selects an item that costs equal to or less than the amount of money that the user put in the vending machine, 
    // the program should display the change returned to the user. 
    private void buyCandy() throws VendingMachinePersistenceException {
        view.displayBuyCandyBanner();
        displayCandySelection();
        view.emptyLine();
        view.currentBalance(service.getBalance(false));
        
        int candyNumber = view.getCandyNumberChoice();
        
        try {
            Candy candyName = service.buyCandy(candyNumber);
            view.displayCandySuccess(candyName.getCandyName());
            view.displayChangeBanner();
            service.getBalance(true);
            // Change must be displayed as the number of quarters, dimes, nickels, and pennies returned to the user.
            System.out.println(service.getBalanceInCoins());
            view.getHitEnter();
        } catch (NoItemInventoryException ex) {
            view.displayErrorMessage(ex.getMessage());
        } catch (InsufficientFundsException ex){
            view.displayErrorMessage(ex.getMessage());
        }
        
    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }    
}  
    

