/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Teresa
 */
public class VendingMachineController {
    
    private VendingMachineView view = new VendingMachineView();
    private UserIO io = new UserIOConsoleImpl();
    
    
    // The program should display all of the items and their respective prices when the program starts, along with an option to exit the program.
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing){
            view.getUserMoney();
            
            menuSelection = getMenuSelection();
            
            switch (menuSelection) {
                case 1: 
                    io.print("Tolberone: $2.00");
                    break;
                case 2:
                    io.print("Reese's: $1.75");
                    break;
                case 3:
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
   
}