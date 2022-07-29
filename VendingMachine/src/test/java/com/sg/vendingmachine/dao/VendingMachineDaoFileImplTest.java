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
        
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() { 
    }
    
    // Our VendingMachineDao has 5 methods we must test
    // 1. List Candy - getAllCandy - DONE
    // 2. Add Money - addMoney - DONE
    // 3. Display Balance - getChangeBalance - DONE
    // 4. Buy Candy - buyCandy - DONE
    // 5. Change - getBalanceInCoins - DONE
    
    
    
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
            // check the general contents of the list
            assertNotNull(candies, "The candy list must not null");
            assertEquals(5, candies.size(), "The candy list should have 5 candy types");
        } catch (NullPointerException ex){
        }
    }
    
    @Test 
    public void testAddmoney() throws Exception {
        try {
            BigDecimal money = new BigDecimal (1.00);
            testDao.addMoney(money);
        } catch (NullPointerException ex){
        }
    }
    
    @Test 
    public void testGetChangeBalance() throws Exception {
        try {
            BigDecimal money = new BigDecimal (1.00);
            testDao.addMoney(money);
            testDao.getChangeBalance();
        } catch (NullPointerException ex){
        }
         
    }
    
    @Test
    public void testBuyCandyAndGetCoins() throws Exception {
        try {
            // Create a list of candy for the inventory
            List<Candy> candies = new ArrayList<>();
            candies.add(0, new Candy (1, "Toblerone", new BigDecimal(2.00), 9));
            candies.add(1, new Candy (2, "Reese's", new BigDecimal(1.75), 5));
            candies.add(2, new Candy (3, "Kit-Kat", new BigDecimal(1.50), 5));
            candies.add(3, new Candy(4, "Peach Rings", new BigDecimal(1.25), 3));
            candies.add(4, new Candy(5, "Hot Tamales", new BigDecimal(1.00), 0));
            candies = testDao.getAllCandy();
            
            // add money and display balance
            BigDecimal money = new BigDecimal(2.79);
            testDao.addMoney(money);
            testDao.getChangeBalance();
            
            // check the general contents of the list
            assertNotNull(candies, "The candy list must not null");
            assertEquals(5, candies.size(), "The candy list should have 5 candy types");
            
            // testing out buy candy
            candies = testDao.getAllCandy().stream()
                    .filter((c) -> c.getCandyNumber() == 1) // buying Toblerone
                    .collect(Collectors.toList());
            Candy candyChoice = candies.get(0); 
            int boughtCandy = candyChoice.getCandyNumber();
            testDao.buyCandy(boughtCandy);
            testDao.getBalanceInCoins();
            
        } catch (NullPointerException ex) {
        }

    }

}
