/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import java.io.FileWriter;
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
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "vendingmachine.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);
    }
}
