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
import java.util.List;

/**
 *
 * @author Teresa
 */
public class VendingMachineController {
    
   // private Change balance = new Change(money);
    private VendingMachineServiceLayer service;
    private VendingMachineView view; // = new VendingMachineView();
    private BigDecimal userMoney = new BigDecimal(0);
    private Change balance = new Change(userMoney);
    private Candy candy;
    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    private UserIO io = new UserIOConsoleImpl();
    
    
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
                        io.print("Buy Candy");
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
        List<Candy> candyList = service.getAllCandy();
        view.displayCandyList(candyList);
    }
    
    private void addMoney() throws VendingMachinePersistenceException {
        view.displayAddMoneyBanner();
        userMoney = view.displayRequestUserMoney();
        balance.addChange(userMoney);
        view.addedMoneySuccessBanner(userMoney);
    }
    
    private void displayBalance() throws VendingMachinePersistenceException {
//        try {
            view.displayBalanceBanner();
            view.currentBalance(userMoney);
            //service.noBalance();
            
//        } catch (NoMoneyException ex){
//            view.displayErrorMessage(ex.getMessage());
////            //service.getBalance(true);
//        } 
    }
    // call view to display enter selection id, get item id from user, change to int
    // call purchase, 
    // wrap try service.makePurchase (buy candy)}
    // balance = service.getBalance display successfully purchased}
    // take id, using view select item
    // use BigBalance intialize at ;
    
    private void buyCandy() throws VendingMachinePersistenceException {
        view.displayBuyCandyBanner();
        displayCandySelection();
        
//        try {
//            service.noBalance();
//            view.currentBalance(balance.getBalance());
////        } catch (NoMoneyException ex){
//            view.displayErrorMessage(ex.getMessage());
//        } 
//            int candyNumber = view.getCandyNumberChoice();
//            String candyName = candy.getCandyName();
        
        try {
            //service.noBalance();
            view.currentBalance(userMoney);
            int candyNumber = view.getCandyNumberChoice();
            String candyName = candy.getCandyName();
            service.buyCandy(candyNumber);
            //service.getBalance(true);
            //balance = service.getBalance((false);
            view.displayCandySuccess(candyName);
            view.displayChangeBanner();
            view.displayChange(candyName, candyNumber);
//        } catch (NoMoneyException ex){
//            view.displayErrorMessage(ex.getMessage());    
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
    
//        view.displayBuyCandyBanner();
//        view.displaySelectionBanner();
//        List<Candy> candyList = service.getAllCandy();
//        view.displayCandyList(candyList);
//        int candyNumber = view.getCandyNumberChoice(balance);
//      
//        try {
//            Candy candyName = view.getCandyNumberChoice(candyNumber);
//              //dao.buyCandy(userChoice);
//            service.buyCandy(candyNumber);
//            view.displayCandySuccess(candyName);
////            // modify this
////            userChange.makePurchase(candy.getCandyPrice());
////            view.displayChangeBanner();
////            userChange = new Change(userChange.getBalance());
////            System.out.println(userChange.toString());
////            hasErrors = false;
//            balance = service.buyCandy(candyNumber);
//            balance = service.getBalance(false);
//            view.displaySuccessfulPurchase(itemName);
//            view.displayBalance(balance);
//        } catch (NoItemInventoryException ex) {
//            view.displayErrorMessage(ex.getMessage());
//        } catch (InsufficientFundsException ex){
//            view.displayErrorMessage(ex.getMessage());
//        }
//        
    //}
    
    
            
//        BigDecimal money = view.displayRequestUserMoney();
//        MathContext roundingUp = new MathContext(money.toString().length());
//        money = money.round(roundingUp);
//        Change userChange = new Change(money);
//            
//        view.displayBuyCandyBanner();
//        view.displaySelectionBanner();
//        List<Candy> candyList = service.getAllCandy();
//        view.displayCandyList(candyList);
//            
//        boolean hasErrors = false;
//        do {
//            String userChoice = view.getCandyNumberChoice(money);
//            Candy candy = service.getOneCandy(userChoice);
//        try {
//            //dao.buyCandy(userChoice);
//            service.buyCandy(userChoice);
//            view.displayCandySuccess(candy);
//            // modify this
//            userChange.makePurchase(candy.getCandyPrice());
//            view.displayChangeBanner();
//            userChange = new Change(userChange.getBalance());
//            System.out.println(userChange.toString());
//            hasErrors = false;
//            } catch (NoItemInventoryException e) {
//                hasErrors = true;
//                view.displayErrorMessage(e.getMessage());
//                 // removed haserrors here
//                //int candyQuantity = candy.getCandyQuantity();
//               // while (candyQuantity == 0) {
////                    view.displayOutOfStock(candy);
//                view.displayBuyCandyBanner();
//                view.displayCandyList(candyList);
//                   // userChoice = view.getCandyNumberChoice(money);
//                    //candy = service.getOneCandy(userChoice);
//                    //candyQuantity = candy.getCandyQuantity();
//                //}
//                
//            while (userChange.getBalance().compareTo(candy.getCandyPrice()) == -1) {
//                    // display a new banner that says Insufficient funds
//                view.notEnoughMoney(userChange.getBalance()); // change to service layer
//                    // prompt the user to input more money -- create in view
//                money = view.addMoreMoney();
//                    // add the money inputed to the userChange object, using addChange balance
//                userChange.addChange(money);
//                }
//                
////                } catch (InsufficientFundsException e) {
////                    hasErrors = true;
////                    view.displayErrorMessage(e.getMessage());
////                }
//                
//            } //while (hasErrors);
//            } while(hasErrors);
//            view.emptyLine();
//            view.getHitEnter();
        //}
            
    
//    private void unknownCommand() {
//        view.displayUnknownCommandBanner();
//    }
//    
//    private void exitMessage() {
//        view.displayExitBanner();
//    }    
//}
    // listing single candy information
//    private void viewCandy() {
//        view.displayCandyBanner();
//        String candyNumber = view.getCandyNumberChoice();
//        Candy candy = dao.getCandy(candyNumber);
//        view.displayCandy(candy);
//    }
//    
    

