/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.Candy;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class VendingMachineController {
    
    //private Change change = new Change(balance);
    private VendingMachineServiceLayer service;
    private VendingMachineView view; // = new VendingMachineView();
    private BigDecimal balance = new BigDecimal(0);
    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    // private UserIO io = new UserIOConsoleImpl();
    
    
    // The program should display all of the items and their respective prices when the program starts, along with an option to exit the program.
    public void run() {
        
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
            while (keepGoing){

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1: 
                       displayCandySelection();
                        break;
                    case 2:
                        buyCandy();
                        break;
                    case 3:
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
        view.displayCandyBanner();
        view.displaySelectionBanner();
        List<Candy> candyList = service.getAllCandy();
        view.displayCandyList(candyList);
        view.getHitEnter();
    }
    
    private void buyCandy() throws VendingMachinePersistenceException {
            
            BigDecimal money = view.displayRequestUserMoney();
            MathContext roundingUp = new MathContext(money.toString().length());
            money = money.round(roundingUp);
            Change userChange = new Change(money);
            
            view.displayBuyCandyBanner();
            view.displaySelectionBanner();
            List<Candy> candyList = service.getAllCandy();
            view.displayCandyList(candyList);
            
            boolean hasErrors = false;
            do {
                String userChoice = view.getCandyNumberChoice(money);
                Candy candy = service.getOneCandy(userChoice);
            try {
                service.buyCandy(candy);
                int candyQuantity = candy.getCandyQuantity();
                while (candyQuantity == 0) {
                    view.displayOutOfStock(candy);
                    view.displayCandyList(candyList);
                    userChoice = view.getCandyNumberChoice(money);
                    candy = service.getOneCandy(userChoice);
                    candyQuantity = candy.getCandyQuantity();
                }
                hasErrors = false;
                } catch (NoItemInventoryException e) {
                    hasErrors = true;
                    view.displayErrorMessage(e.getMessage());
                }
                while (userChange.getBalance().compareTo(candy.getCandyPrice()) == -1) {
                    // display a new banner that says Insufficient funds
                    view.notEnoughMoney(userChange.getBalance()); // change to service layer
                    // prompt the user to input more money -- create in view
                    money = view.addMoreMoney();
                    // add the money inputed to the userChange object, using addChange balance
                    userChange.addChange(money);
                }
                //dao.buyCandy(userChoice);
                view.displayCandySuccess(candy);
                // modify this
                userChange.makePurchase(candy.getCandyPrice());
                view.displayChangeBanner();
                userChange = new Change(userChange.getBalance());
                System.out.println(userChange.toString());
                hasErrors = false;
//                } catch (InsufficientFundsException e) {
//                    hasErrors = true;
//                    view.displayErrorMessage(e.getMessage());
//                }
                view.emptyLine();
                view.getHitEnter();
            } while (hasErrors);

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
    

