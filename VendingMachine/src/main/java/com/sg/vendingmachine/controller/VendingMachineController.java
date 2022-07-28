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
    
    // call view to display enter selection id, get item id from user, change to int
    // call purchase, 
    // wrap try service.makePurchase (buy candy)}
    // balance = service.getBalance display successfully purchased}
    // take id, using view select item
    // use BigBalance intialize at ;
    
    // tell the user they need to add more money... 
    // If the user selects an item that costs more than the amount the user put into the vending machine, 
    // the program should display a message indicating insufficient funds
    // and then redisplay the amount the user had put into the machine. 
    
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
    
    // listing single candy information
//    private void viewCandy() {
//        view.displayCandyBanner();
//        String candyNumber = view.getCandyNumberChoice();
//        Candy candy = dao.getCandy(candyNumber);
//        view.displayCandy(candy);
//    }
//    
    

