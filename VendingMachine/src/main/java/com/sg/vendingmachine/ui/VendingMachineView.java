/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

/**
 *
 * @author Teresa
 */
public class VendingMachineView {
    
    private UserIO io = new UserIOConsoleImpl();
    
   // The user must put in some amount of money before an item can be selected.
    public String getUserMoney() {
       return io.readString("How much money do you have to spend?");
    }
    
    public int printMenuAndGetSelection() {
        io.print("=== Candy Machine ===");
        io.print("1. Tolberone: $2.00");
        io.print("2. Reese's: $1.75");
        io.print("3. Kit-Kat: $1.50");
        io.print("4. Peach Rings: $1.25");
        io.print("5. Jelly Beans: $1.00");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
}
