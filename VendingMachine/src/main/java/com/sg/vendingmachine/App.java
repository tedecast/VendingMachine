/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import java.util.Scanner;

/**
 *
 * @author Teresa
 */
public class App {
    
    public static void main (String[] args) {
        // The user must put in some amount of money before an item can be selected.
        Scanner userInput = new Scanner(System.in);
        
        // Should be in double
        System.out.println("How much money do you have to spend?");
        double moneyInserted = Double.parseDouble(userInput.nextLine());
        // Output results after money is inserted
        System.out.println(moneyInserted);
        
        VendingMachineController controller = new VendingMachineController();
        controller.run();
    }
}
