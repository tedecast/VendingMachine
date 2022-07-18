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
        
        // The program should display all of the items and their respective prices when the program starts, along with an option to exit the program.

        System.out.println("Here's your options for candy:");
        System.out.println("");
        System.out.println("=== Candy Machine ===");
        System.out.println("1. Tolberone: $2.00");
        System.out.println("2. Reese's: $1.75");
        System.out.println("3. Kit-Kat: $1.50");
        System.out.println("4. Peach Rings: $1.25");
        System.out.println("5. Jelly Beans: $1.00");
        System.out.println("6. Exit");
        
        System.out.println("");
        System.out.println("Which candy would you like to purchase?");
        String candy = userInput.nextLine();
//        Change candyChoice = Change.valueOf(candy.toUpperCase());
       
        
        // Output results after money is inserted
        
        //System.out.println(moneyInserted);
        
        VendingMachineController controller = new VendingMachineController();
        controller.run();
    }
}
