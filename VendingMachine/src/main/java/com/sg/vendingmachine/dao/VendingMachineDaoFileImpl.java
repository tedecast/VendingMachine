/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Candy;
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

/**
 *
 * @author Teresa
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    
    private Map<String, Candy> candies = new HashMap<>();
    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    @Override
    public List<Candy> getAllCandy() {
        return new ArrayList<>(candies.values());
    }

    @Override
    public Candy getOneCandy(String candyNumber) {
        return candies.get(candyNumber);
    }

    @Override
    public Candy purchaseCandy(String candyNumber) {
       Candy purchasedCandy = getOneCandy(candyNumber);
       return purchasedCandy;
    }
    
    private Candy unmarshallCandy(String candyAsText){
        
        String[] candyTokens = candyAsText.split(DELIMITER);
        
        String candyNumber = candyTokens[0];
        
        Candy candyFromFile = new Candy(candyNumber);
        
        candyFromFile.setCandyName(candyTokens[1]);
        
        candyFromFile.setCandyPrice(new BigDecimal(candyTokens[2]));
        
        candyFromFile.setCandyQuantity(Integer.parseInt((candyTokens[3])));
        
        return candyFromFile;
    }
    
    private void loadInventory() throws VendingMachineDaoException {
        
        Scanner scanner;
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException(
                    "-_- Could not load inventory into memory.", e);
        }
        
        String currentLine;
        Candy currentCandy;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentCandy = unmarshallCandy(currentLine);
            
            candies.put(currentCandy.getCandyNumber(), currentCandy);
        }
        scanner.close();
    }
    
    private String marshallCandy(Candy aCandy){
        
        String candyAsText = aCandy.getCandyNumber() + DELIMITER;
        
        candyAsText += aCandy.getCandyName() + DELIMITER;
        
        candyAsText += aCandy.getCandyPrice() + DELIMITER;
        
        candyAsText += aCandy.getCandyQuantity() + DELIMITER;
        
        return candyAsText;
    }
    
    private void writeInventory() throws VendingMachineDaoException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachineDaoException (
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
