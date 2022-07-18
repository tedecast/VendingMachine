/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.Candy;
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
    
    private VendingMachineView view = new VendingMachineView();
    private VendingMachineDao dao = new VendingMachineDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();
    
    
    // The program should display all of the items and their respective prices when the program starts, along with an option to exit the program.
    public void run() {
        
        boolean keepGoing = true;
        int menuSelection = 0;
        
        while (keepGoing){
            //view.getUserMoney();
            
            menuSelection = getMenuSelection();
            
            switch (menuSelection) {
                case 1: 
                   displayCandySelection();
                    break;
                case 2:
                    io.print("Buy Candy");
                    break;
                case 3:
                    viewCandy();
                    break;
                case 4:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }
        }
        io.print("GOOD BYE");
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    // try { list items
    private void displayCandySelection() {
        view.displayAllCandyBanner();
        List<Candy> candyList = dao.getAllCandy();
        view.displayCandyList(candyList);
    }
    
    // listing single candy information
    private void viewCandy() {
        view.displayCandyBanner();
        String candyNumber = view.getCandyNumberChoice();
        Candy candy = dao.getCandy(candyNumber);
        view.displayCandy(candy);
    }
    
    private BigDecimal requestDeposit() {
        BigDecimal deposit = view.displayRequestDeposit();
        view.displayDepositSuccessful();
        return deposit;
    }
    
}
