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
    
    // Vending machine items must be stored in a file.
    // When an item is vended, the program must update the inventory level appropriately.
    // However, the items that have an inventory level of zero must still be read
    // If the machine runs out of an item, it should no longer be available as an option to the user
    public void displayCandyList(List<Candy> candyList) {
        for (Candy currentCandy : candyList) {
            io.print("|  " + currentCandy.getCandyNumber() + "   |"
            + currentCandy.getCandyName() + "| $"
            + currentCandy.getCandyPrice() + "  |   " 
            + currentCandy.getCandyQuantity() + "   |");
        }
    }
    
    // The program should display all of the items and their respective prices when the program starts, along with an option to exit the program.
    public int printMenuAndGetSelection(){
        io.print("\n======   Main Menu   ======");
        io.print("1. List Candy");
        io.print("2. Add Money");
        io.print("3. Display Balance");
        io.print("4. Buy Candy");
        io.print("5. Exit");
        
        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    public void displayCandyBanner() {
        io.print("\n. * . * . * .  Candy  . * . * . * . * .");   
    }
    
    public void displayBuyCandyBanner() {
        io.print("\n. * . * . *  Buy Candy  * . * . * . * . ");
 
    }
    
    // The program must track the following properties for each item:
    // Item Name, Item Cost, Number of Items in Inventory
    public void displaySelectionBanner() {
        io.print("- - - - - - - - - - - - - - - - - - - - ");
        io.print("|Number|     Name    |  Cost  |  QTY  |");
    }
    
    public void emptyLine() {
        io.print("");
    }
    
    public void getHitEnter() {
        io.readString("\nPlease hit enter to continue.");
    }
    
    public void displayAddMoneyBanner() {
        emptyLine();
        io.print("* . * . * . Add Money . * . * . * ");
    }
    // The user must put in some amount of money before an item can be selected.
    public BigDecimal displayRequestUserMoney() {
        // changed Float to BigDecimal from UserIO
        BigDecimal money = io.readBigDecimal("How much money do you want to add?");
        return money;
    }
    
    public void addedMoneySuccessBanner(BigDecimal money) {
        io.print("You've successfully added $" + money);
        getHitEnter();
    }
    
    public void displayBalanceBanner() {
        emptyLine();
        io.print("* . * . * . Balance . * . * . * ");
    }
    
    public void currentBalance(BigDecimal money) {
        io.print("Your current balance is $" + money );
    }
    
    // Only one item can be vended at a time.
    public int getCandyNumberChoice() {
        return io.readInt("Please enter the Candy's Number you'd like to purchase.", 1, 5); //change this to be dynamic (maxInt OR getAllCandy.length - 1
    }
        
    public void displayCandySuccess(String candyName) {
        io.print("");
        io.print("Thank you for your purchase of:");
        io.print("        " + candyName);// .getCandyName()); //+ " | " + "$" + candy.getCandyPrice());
        emptyLine();
    }
    
    // If the user selects an item that costs equal to or less than the amount of money,
    // the program should display the change returned to the user.
    // Change must be displayed as the number of quarters, dimes, nickels, and pennies returned to the user.
    public void displayChangeBanner(){
        io.print("* . * . Your change is: . * . *");
    }
    
    // balance = userChange
    public void displayChange(BigDecimal balance){
        io.print(balance.toString());
    }
   
    public void displayErrorMessage(String errorMsg){
        io.print("\n. * . * .   ERROR   . * . * . ");
        io.print(errorMsg);
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }
    
    public void displayExitBanner() {
        io.print("Goodbye!!!");
    }
}
    
