/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import static com.sg.vendingmachine.dao.VendingMachineDaoFileImpl.DELIMITER;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Teresa
 */
public class VendingMachineDaoFileImplTest {
    // Our VendingMachineDao has 5 methods we must test
    // 1. List Candy - getAllCandy
    // 2. Add Money - addMoney
    // 3. Display Balance - getChangeBalance
    // 4. Buy Candy - buyCandy
    // 5. Change - getBalanceInCoins
    
    
        
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() { 
//        candies.put("candyOne", new Candy (1, "Toblerone", new BigDecimal(2.00), 9));
//        candies.put("candyTwo", new Candy (2, "Reese's", new BigDecimal(1.75), 5));
//        candies.put("candyThree", new Candy (3, "Kit-Kat", new BigDecimal(1.50), 5));
//        candies.put("candyFour", new Candy(4, "Peach Rings", new BigDecimal(1.25), 3));
//        candies.put("candyFive", new Candy(5, "Hot Tamales", new BigDecimal(1.00), 0));


    }
    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testinventory.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);
    }
    
    @Test
    public void testGetAllCandy() throws Exception {
        try {
            List<Candy> candies = new ArrayList<>();
            candies.add(0, new Candy (1, "Toblerone", new BigDecimal(2.00), 9));
            candies.add(1, new Candy (2, "Reese's", new BigDecimal(1.75), 5));
            candies.add(2, new Candy (3, "Kit-Kat", new BigDecimal(1.50), 5));
            candies.add(3, new Candy(4, "Peach Rings", new BigDecimal(1.25), 3));
            candies.add(4, new Candy(5, "Hot Tamales", new BigDecimal(1.00), 0));
            candies = testDao.getAllCandy();
        } catch (NullPointerException ex){
        }
    }
    
//    @Test
//    public void testAddMoney(BigDecimal money) throws Exception {
//        Change userChange = new Change(new BigDecimal(0));
//        testDao.addMoney(money);
////        if (money.compareTo(BigDecimal.ZERO) == 1) {
////            userChange.addChange(money);
////        }
//    }
    
//    @Test 
//    public BigDecimal testGetChangeBalance() throws Exception {
//        try {
//            testDao.getChangeBalance();
//            BigDecimal balance = userChange.getBalance();
//            return balance;
//        } catch (NullPointerException ex) {
//        }
//        return new BigDecimal("0");
//    }

//    @Test
//    public Candy testBuyCandy(int candyNumber) throws Exception {
//        try {
//            loadTestInventory();
//            List<Candy> filteredCandyList = testGetAllCandy().stream()
//                    .filter((c) -> c.getCandyNumber() == candyNumber)
//                    .collect(Collectors.toList());
//            Candy candyChoice = filteredCandyList.get(0);
//            int candyQuantity = candyChoice.getCandyQuantity();
//            BigDecimal candyPrice = candyChoice.getCandyPrice();
//            userChange.makePurchase(candyPrice);
//            candyChoice.setCandyQuantity(candyQuantity - 1);
//            if (candyQuantity > 0) {
//                testDao.writeInventory();
//            }
//            return candyChoice;
//        } catch (NullPointerException ex) {
//        }
//        return new Candy(1, "candyName", new BigDecimal(0), 2);
//    }
//    
//    
//   private Candy unmarshallCandy(String candyAsText) throws Exception{
//        
//        String[] candyTokens = candyAsText.split(DELIMITER);
//        
//        // converted from string to int
//        int candyNumber = Integer.parseInt(candyTokens[0]); 
//        String candyName = candyTokens[1];
//        BigDecimal candyPrice = new BigDecimal (candyTokens[2]);
//        int candyQuantity = Integer.parseInt(candyTokens[3]);
//        
//        Candy candyFromFile = new Candy(candyNumber, candyName, candyPrice, candyQuantity);
//        
//        return candyFromFile;
//    }
//    
//    private void loadTestInventory() throws Exception {
//        
//        Scanner scanner;
//        
//        try {
//            // Create Scanner for reading the file
//            scanner = new Scanner(
//                    new BufferedReader(
//                            new FileReader(INVENTORY_FILE)));
//        } catch (FileNotFoundException e) {
//            throw new VendingMachinePersistenceException(
//                    "-_- Could not load inventory into memory.", e);
//        }
//        
//        String currentLine;
//        Candy currentCandy;
//        
//        while (scanner.hasNextLine()) {
//            currentLine = scanner.nextLine();
//            currentCandy = unmarshallCandy(currentLine);
//            
//            // converted to int
//            // convert int to string
//            candies.put(String.valueOf(currentCandy.getCandyNumber()), currentCandy);
//        }
//        scanner.close();
//    }
//    
//    private String marshallCandy(Candy aCandy) {
//        
//        String candyAsText = aCandy.getCandyNumber() + DELIMITER;
//        
//        candyAsText += aCandy.getCandyName() + DELIMITER;
//        
//        candyAsText += aCandy.getCandyPrice() + DELIMITER;
//        
//        candyAsText += aCandy.getCandyQuantity() + DELIMITER;
//        
//        return candyAsText;
//    }
//    
//    private void writeTestInventory() throws VendingMachinePersistenceException {
//        
//        PrintWriter out;
//        
//        try {
//            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
//        } catch (IOException e) {
//            throw new VendingMachinePersistenceException (
//                    "Could not save candy data.", e);
//        }
//        
//        String candyAsText;
//        List<Candy> candyList = this.getAllCandy();
//        
//        for (Candy currentCandy : candyList) {
//            // turn a Candy into String
//            candyAsText = marshallCandy(currentCandy);
//            // write the Candy object to the file
//            out.println(candyAsText);
//            // for PrintWriter to write line to the file
//            out.flush();
//        }
//        // Clean up
//        out.close();
//    }
}
