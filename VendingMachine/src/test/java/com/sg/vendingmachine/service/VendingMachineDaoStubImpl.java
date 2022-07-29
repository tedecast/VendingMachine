/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Candy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    public Candy onlyCandy = new Candy (1, "Toblerone", new BigDecimal(2.00), 9);

    @Override
    public List<Candy> getAllCandy() throws VendingMachinePersistenceException {
        List<Candy> candies = new ArrayList<>();
        candies.add(onlyCandy);
        return candies;
//        candies.add(0, new Candy(1, "Toblerone", new BigDecimal(2.00), 9));
//        candies.add(1, new Candy(2, "Reese's", new BigDecimal(1.75), 5));
//        candies.add(2, new Candy(3, "Kit-Kat", new BigDecimal(1.50), 5));
//        candies.add(3, new Candy(4, "Peach Rings", new BigDecimal(1.25), 3));
//        candies.add(4, new Candy(5, "Hot Tamales", new BigDecimal(1.00), 0));
    }

    @Override
    public void addMoney(BigDecimal money) throws VendingMachinePersistenceException {
    }

    @Override
    public BigDecimal getChangeBalance() throws VendingMachinePersistenceException {
        BigDecimal balance = new BigDecimal(0);
        return balance;
    }

    @Override
    public Candy buyCandy(int candyNumber) throws VendingMachinePersistenceException {
        List<Candy> candies = new ArrayList<>();
        candies.add(onlyCandy);
        onlyCandy.buyCandy();
        return onlyCandy;
    }

    @Override
    public String getBalanceInCoins() throws VendingMachinePersistenceException {
        return "";
    }
    
}
