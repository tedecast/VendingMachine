/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Candy;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Coins;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class VendingMachineView {
    
    private UserIO io; //= new UserIOConsoleImpl();
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    // The program should display all of the items and their respective prices when the program starts, along with an option to exit the program.
    public int printMenuAndGetSelection() {
        io.print("======   Main Menu   ======");
        io.print("1. Display Candy Selection");
        io.print("2. Buy Candy");
        io.print("3. Exit");
        
        return io.readInt("Please select from the above choices.", 1, 3);
    }
    public void getBanner() {
        io.print("- - - - - - - - - - - - - - - - - - - - ");
    }
    
    public void displayCandyBanner() {
        emptyLine();
        io.print(". * . * . * .  Candy  . * . * . * . * .");   
        getBanner();
    }
    
    public void displayBuyCandyBanner() {
        emptyLine();
        io.print(". * . * . *  Buy Candy  * . * . * . * . ");
        getBanner();
    }
    
    // The program must track the following properties for each item:
    // Item Name, Item Cost, Number of Items in Inventory
    public void displaySelectionBanner() {
        io.print("|Number|     Name    |  Cost  |  QTY  |");
    }
    
    // Vending machine items must be stored in a file.
    // When an item is vended, the program must update the inventory level appropriately.
    // However, the items that have an inventory level of zero must still be read
    public void displayCandyList(List<Candy> candyList) {
        for (Candy currentCandy : candyList) {
            io.print("|  " + currentCandy.getCandyNumber() + "   |"
            + currentCandy.getCandyName() + "| $"
            + currentCandy.getCandyPrice() + "  |   " 
            + currentCandy.getCandyQuantity() + "   |");
        }
        emptyLine();
    }
    
    public void emptyLine() {
        io.print("");
    }
    
    public void getHitEnter() {
        io.readString("Please hit enter to continue.");
    }
    
    // The user must put in some amount of money before an item can be selected.
    public BigDecimal displayRequestUserMoney() {
        Float money = io.readFloat("How much money do you have to spend?");
        BigDecimal moneyBD = new BigDecimal(money);
        return moneyBD;
    }
    
//    public void moneyIn(BigDecimal money) {
//        io.print("You put in: $" + money);
//    }
    // Only one item can be vended at a time.
    public String getCandyNumberChoice(BigDecimal money) {
        io.print("You put in $" + money);
        int choice = io.readInt("Please enter the Candy's Number you'd like to purchase.", 1, 5);
        String stringChoice = String.valueOf(choice);
        return stringChoice;
    }
    
    // If the machine runs out of an item, it should no longer be available as an option to the user
    public void displayOutOfStock(Candy candy) {
        io.print("Sorry, we're out of stock of " + candy.getCandyName() + ".");
    }
    
    // If the user selects an item that costs more than the amount the user put into the vending machine, 
    // the program should display a message indicating insufficient funds
    // and then redisplay the amount the user had put into the machine.
    public void returnMoney(BigDecimal money) {
        io.print("Insufficent funds. Here's your $" + money + " back.");
        emptyLine();
        getHitEnter();
    } 
    
    public void displayCandySuccess(Candy candy) {
        io.print("Thank you for your purchase of:");
        io.print("   " + candy.getCandyName() + " | " + "$" + candy.getCandyPrice());
        emptyLine();
    }
    
    // If the user selects an item that costs equal to or less than the amount of money,
    // the program should display the change returned to the user.
    // Change must be displayed as the number of quarters, dimes, nickels, and pennies returned to the user.
    public void displayChangeBanner(){
        io.print("* . * . Your change is: . * . *");
    }
    
    public void displayChange(String coinName, int coinAmount){
        io.print("          " + coinAmount  + " " + coinName);
    }
   
    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }
    
    public void displayExitBanner() {
        io.print("Goodbye!!!");
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
//    
//    public void candyCost(Candy candy) {
//        io.print("The cost for " + candy.getCandyName() + " is " + candy.getCandyPrice()); 
//    }
//
//  