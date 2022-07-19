/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Candy;
import java.math.BigDecimal;
import java.util.List;

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
    
    // The program should display all of the items and their respective prices when the program starts, along with an option to exit the program.
    public int printMenuAndGetSelection() {
        io.print("=== Main Menu ===");
        io.print("1. Display Candy Selection");
        io.print("2. Buy Candy");
        io.print("3. Exit");
        
        return io.readInt("Please select from the above choices.", 1, 3);
    }
    
    public void displayCandyBanner() {
        io.print("=== Candy === ");
    }
    
    public void displayCandyList(List<Candy> candyList) {
        for (Candy currentCandy : candyList) {
            io.print(currentCandy.getCandyNumber() + ": "
            + currentCandy.getCandyName() + " "
            + currentCandy.getCandyPrice() + " " 
            + currentCandy.getCandyQuantity());
        }
    }
    
    public void displayCandyPurchaseBanner() {
        io.print("=== Purchase Candy ===");
    }
    
    public BigDecimal displayRequestMoney() {
        Float money = io.readFloat("How much money do you want to put in for purchase?");
        BigDecimal moneyBD = new BigDecimal(money);
        return moneyBD;
    }
    
    public String getCandyNumberChoice() {
        int choice = io.readInt("Please enter the Candy's Number you'd like to purchase.", 1, 5);
        String stringChoice = String.valueOf(choice);
        return stringChoice;
    }
    
    public void displayDepositSuccessful() {
        io.print("Deposit successful");
    }
    
    public void displayChange(String change){
        io.print("Your change is " + change);
    }
    
    public String displayThankYou() {
        return io.readString("Thank you for your purchase!");
    }
   
    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }
    
    public void displayExitBanner() {
        io.print("Goodbye!");
    }
    

//    public BigDecimal displayRequestDeposit() {
//        Double cash = io.readDouble("Please deposit money.");
//        BigDecimal bigDecimal = new BigDecimal(cash);
//        return bigDecimal;
//    }
    
    
//    
//    public void displayCandy(Candy candy) {
//        if (candy != null) {
//            io.print(candy.getCandyNumber());
//            io.print(candy.getCandyName());
//            io.printBigDecimal(candy.getCandyPrice());
//            io.printInt(candy.getCandyQuantity());
//            io.print("");
//        } else {
//            io.print("No such candy exists.");
//        }
//        io.readString("Please hit enter to continue.");
//    }
    
    
//    public int printCandyMenuAndGetSelection() {
//        io.print("=== Candy Selection ===");
//name, 
////        io.print("1. Tolberone: $2.00 quantity");
////        io.print("2. Reese's: $1.75");
////        io.print("3. Kit-Kat: $1.50");
////        io.print("4. Peach Rings: $1.25");
////        io.print("5. Jelly Beans: $1.00");
////        io.print("6. Exit");
//
//        //return io.readInt("Please select from the above choices.", 1, 3);
//    }
}
