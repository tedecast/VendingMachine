/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.Candy;
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
    
    private VendingMachineView view; // = new VendingMachineView();
    private VendingMachineDao dao; //= new VendingMachineDaoFileImpl();
    public VendingMachineController(VendingMachineDao dao, VendingMachineView view) {
        this.dao = dao;
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
        } catch (VendingMachineDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void displayCandySelection() throws VendingMachineDaoException {
        view.displayCandyBanner();
        view.displaySelectionBanner();
        List<Candy> candyList = dao.getAllCandy();
        view.displayCandyList(candyList);
        view.getHitEnter();
    }
    
    private void buyCandy() throws VendingMachineDaoException {
        BigDecimal money = view.displayRequestUserMoney();
        view.displayBuyCandyBanner();
        view.displaySelectionBanner();
        List<Candy> candyList = dao.getAllCandy();
        view.displayCandyList(candyList);
        
        String userChoice = view.getCandyNumberChoice();
        Candy candy = dao.buyCandy(userChoice);
        int candyQuantity = candy.getCandyQuantity();
        
        while (candyQuantity == 0) {
            view.displayOutOfStock(candy);
            view.displayCandyList(candyList);
            userChoice = view.getCandyNumberChoice();
            candy = dao.buyCandy(userChoice);
            candyQuantity = candy.getCandyQuantity();
            //comparing less than
            
        } if (money.compareTo(candy.getCandyPrice()) == -1) {
            MathContext roundingUp = new MathContext(2);
            money = money.round(roundingUp);
//            money = money.setScale(2, RoundingMode.FLOOR);
            // put in view\
            System.out.println("Insufficent funds. Here's your $" + money + " back.");
            view.emptyLine();
            view.getHitEnter();
            
        } else {
            candy.buyCandy();
            
        }
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    // listing single candy information
//    private void viewCandy() {
//        view.displayCandyBanner();
//        String candyNumber = view.getCandyNumberChoice();
//        Candy candy = dao.getCandy(candyNumber);
//        view.displayCandy(candy);
//    }
//    
    
    
    
//    private BigDecimal requestDeposit() {
//        BigDecimal deposit = view.displayRequestDeposit();
//        view.displayDepositSuccessful();
//        return deposit;
//    }
    
}
