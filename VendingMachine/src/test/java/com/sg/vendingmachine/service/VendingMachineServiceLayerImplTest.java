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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Teresa
 */
public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerImplTest() {
        // wire the Service Layer with stub implementations of the Dao and 
        // Audit Dao
//        VendingMachineDao dao = 
//            new VendingMachineDaoStubImpl(new Candy(1, "Toblerone", new BigDecimal(2.00), 2));
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
//        
//        service = new VendingMachineServiceLayerImpl(dao, auditDao);
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayerImpl.class);
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
             List<Candy> candies = new ArrayList<>();
            candies.add(0, new Candy(1, "Toblerone", new BigDecimal(2.00), 9));
            candies.add(1, new Candy(2, "Reese's", new BigDecimal(1.75), 5));
            candies.add(2, new Candy(3, "Kit-Kat", new BigDecimal(1.50), 5));
            candies.add(3, new Candy(4, "Peach Rings", new BigDecimal(1.25), 3));
            candies.add(4, new Candy(5, "Hot Tamales", new BigDecimal(1.00), 0));
            candies = service.getAllCandy();
        } catch (VendingMachinePersistenceException e){
            fail("List was valid. No exception should have been thrown.");
        }
    }
    
    @Test
    public void testInsuffcientFundsExceptions() {
        try {
            List<Candy> candies = new ArrayList<>();
            candies.add(0, new Candy(1, "Toblerone", new BigDecimal(2.00), 9));
            candies.add(1, new Candy(2, "Reese's", new BigDecimal(1.75), 5));
            candies.add(2, new Candy(3, "Kit-Kat", new BigDecimal(1.50), 5));
            candies.add(3, new Candy(4, "Peach Rings", new BigDecimal(1.25), 3));
            candies.add(4, new Candy(5, "Hot Tamales", new BigDecimal(1.00), 0));
            candies = service.getAllCandy();
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
                | NoItemInventoryException e) {
            return;
        }

    }

    @Test
    public void testNoItemInventoryException() {
        try {
            List<Candy> candies = new ArrayList<>();
            candies.add(0, new Candy(1, "Toblerone", new BigDecimal(2.00), 0));
            candies.add(1, new Candy(2, "Reese's", new BigDecimal(1.75), 5));
            candies.add(2, new Candy(3, "Kit-Kat", new BigDecimal(1.50), 5));
            candies.add(3, new Candy(4, "Peach Rings", new BigDecimal(1.25), 3));
            candies.add(4, new Candy(5, "Hot Tamales", new BigDecimal(1.00), 7));
            service.addMoney(new BigDecimal(2.00));
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
                | NoItemInventoryException e) {
            return;
        }
    }
}
