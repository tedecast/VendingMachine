/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;

/**
 *
 * @author Teresa
 */
public class VendingMachineController {
    
    private UserIO io = new UserIOConsoleImpl();
    
    // The program should display all of the items and their respective prices when the program starts, along with an option to exit the program.
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing){
            io.print("=== Candy Machine ===");
            io.print("1. Tolberone: $2.00");
            io.print("2. Reese's: $1.75");
            io.print("3. Kit-Kat: $1.50");
            io.print("4. Peach Rings: $1.25");
            io.print("5. Jelly Beans: $1.00");
            io.print("6. Exit");
            
            menuSelection = io.readInt("Please select from the" + " above choices.", 1, 6 );
            
            switch (menuSelection) {
                case 1: 
                    io.print("Tolberone: $2.00");
                    break;
                case 2:
                    io.print("Reese's: $1.75");
                    break;
                case 3:
                    io.print("Kit-Kat: $1.50");
                    break;
                case 4:
                    io.print("Peach Rings: $1.25");
                    break;
                case 5:
                    io.print("Jelly Beans: $1.00");
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }
        }
    }
    io.print("GOOD BYE");
}
