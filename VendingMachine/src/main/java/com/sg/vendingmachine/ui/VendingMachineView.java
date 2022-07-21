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
        // changed Float to BigDecimal from UserIO
        BigDecimal money = io.readBigDecimal("How much money do you have to spend?");
        //System.out.println(money.toString().length());
        //BigDecimal moneyBD = new BigDecimal(money);
        return money;
    }
    
    
    // tell the user they need to add more money... 
    // If the user selects an item that costs more than the amount the user put into the vending machine, 
    // the program should display a message indicating insufficient funds
    // and then redisplay the amount the user had put into the machine.
    public void notEnoughMoney(BigDecimal money) {
        io.print("Insufficent funds. You only put in $" + money);
//        emptyLine();
//        getHitEnter();
    } 
    
    public BigDecimal addMoreMoney() {
        BigDecimal money = io.readBigDecimal("Please add in more money:");
        return money;
    }
    
    // Only one item can be vended at a time.
    public String getCandyNumberChoice(BigDecimal money) {
        io.print("You put in $" + money);
        int choice = io.readInt("Please enter the Candy's Number you'd like to purchase.", 1, 5);
        String stringChoice = String.valueOf(choice);
        return stringChoice;
    }
    
    // If the machine runs out of an item, it should no longer be available as an option to the user
    public void displayOutOfStock(Candy candy) {
        io.print("Sorry, we're out of stock of:"); //+ candy.getCandyName());
        io.print("      " + candy.getCandyName());
        io.print("Please choose a different Candy's Number to purchase.");
        displayBuyCandyBanner();
    }
    
    
    public void displayCandySuccess(Candy candy) {
        io.print("");
        io.print("Thank you for your purchase of:");
        io.print("        " + candy.getCandyName()); //+ " | " + "$" + candy.getCandyPrice());
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
}
    
