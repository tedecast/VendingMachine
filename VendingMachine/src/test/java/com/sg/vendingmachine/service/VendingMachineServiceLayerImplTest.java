/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Candy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAllCandy() {
        try {
            service.getAllCandy();
        } catch (VendingMachinePersistenceException e){
        // ASSERT
            fail("List was valid. No exception should have been thrown.");
        }
    }
    
    @Test
    public void testExceptions() {
        try {
            service.getAllCandy();
            service.addMoney(new BigDecimal(0));
            service.getBalance(true);
            List<Candy> filteredList = service.getAllCandy().stream()
                    .filter((c) -> c.getCandyNumber() == 1)
                    .collect(Collectors.toList());
            Candy candyChoice = filteredList.get(0);
            int boughtCandy = candyChoice.getCandyNumber();
            service.buyCandy(boughtCandy);
            service.getBalanceInCoins();
            
        } catch (VendingMachinePersistenceException 
                | InsufficientFundsException 
                | NoItemInventoryException e){
            return;
        }
        
}

    }
