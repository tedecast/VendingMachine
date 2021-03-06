/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Candy;
import com.sg.vendingmachine.dto.Change;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Teresa
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    
    private Map<String, Candy> candies = new HashMap<>();
    private Change userChange = new Change(new BigDecimal(0));
    private final String INVENTORY_FILE;
    
    public VendingMachineDaoFileImpl() {
        INVENTORY_FILE = "inventory.txt";
    }
    
    // supposed to be TestFile? Not TextFile?
    public VendingMachineDaoFileImpl(String inventoryTestFile){
        INVENTORY_FILE = inventoryTestFile;
    }
    public static final String DELIMITER = "::"; 
    
    @Override
    public List<Candy> getAllCandy() throws VendingMachinePersistenceException {
        loadInventory();
        return new ArrayList<>(candies.values());
    }
    
    @Override
    public void addMoney(BigDecimal money) throws VendingMachinePersistenceException{
        if (money.compareTo(BigDecimal.ZERO) == 1) {
            userChange.addChange(money);
        }
    }

    @Override
    public BigDecimal getChangeBalance() throws VendingMachinePersistenceException {
        try {
            BigDecimal balance = userChange.getBalance();
            return balance;
        } catch (NullPointerException ex) {
        }
        return new BigDecimal("0");
    }
    
    @Override
    public Candy buyCandy(int candyNumber) throws VendingMachinePersistenceException {
        loadInventory();
        // You must include at least one lambda function in the solution.       
        try {
            List<Candy> filteredCandyList = getAllCandy().stream()
                    .filter((c) -> c.getCandyNumber() == candyNumber)
                    .collect(Collectors.toList());
            Candy candyChoice = filteredCandyList.get(0);
            int candyQuantity = candyChoice.getCandyQuantity();
            BigDecimal candyPrice = candyChoice.getCandyPrice();
            userChange.makePurchase(candyPrice);
            candyChoice.setCandyQuantity(candyQuantity - 1);
            if (candyQuantity > 0) {
                writeInventory();
            }
            return candyChoice;
        } catch (NullPointerException ex) {
        }
        return new Candy(1, "candyName", new BigDecimal(0), 2);
        // hashMap.replace()
        // write it back, and then purchase
    }

    
    @Override
    public String getBalanceInCoins() throws VendingMachinePersistenceException {
        try {
            BigDecimal userBal = userChange.getBalance();
            String coinString = new Change(userBal).toString();
            return coinString;
        } catch (NullPointerException ex) {
        }
        return "";
    }
        
    private Candy unmarshallCandy(String candyAsText) throws VendingMachinePersistenceException{
        
        String[] candyTokens = candyAsText.split(DELIMITER);
        
        // converted from string to int
        int candyNumber = Integer.parseInt(candyTokens[0]); 
        String candyName = candyTokens[1];
        BigDecimal candyPrice = new BigDecimal (candyTokens[2]);
        int candyQuantity = Integer.parseInt(candyTokens[3]);
        
        Candy candyFromFile = new Candy(candyNumber, candyName, candyPrice, candyQuantity);
        
        return candyFromFile;
    }
    
    private void loadInventory() throws VendingMachinePersistenceException {
        
        Scanner scanner;
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load inventory into memory.", e);
        }
        
        String currentLine;
        Candy currentCandy;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentCandy = unmarshallCandy(currentLine);
            
            // converted to int
            // convert int to string
            candies.put(String.valueOf(currentCandy.getCandyNumber()), currentCandy);
        }
        scanner.close();
    }
    
    private String marshallCandy(Candy aCandy) {
        
        String candyAsText = aCandy.getCandyNumber() + DELIMITER;
        
        candyAsText += aCandy.getCandyName() + DELIMITER;
        
        candyAsText += aCandy.getCandyPrice() + DELIMITER;
        
        candyAsText += aCandy.getCandyQuantity() + DELIMITER;
        
        return candyAsText;
    }
    
    private void writeInventory() throws VendingMachinePersistenceException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException (
                    "Could not save candy data.", e);
        }
        
        String candyAsText;
        List<Candy> candyList = this.getAllCandy();
        
        for (Candy currentCandy : candyList) {
            // turn a Candy into String
            candyAsText = marshallCandy(currentCandy);
            // write the Candy object to the file
            out.println(candyAsText);
            // for PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
