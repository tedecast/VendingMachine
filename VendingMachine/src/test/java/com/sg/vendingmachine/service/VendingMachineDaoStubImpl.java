/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Candy;
import com.sg.vendingmachine.dto.Change;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    public Candy onlyCandy;
    
    public VendingMachineDaoStubImpl(){
        onlyCandy.setCandyNumber(1);
        onlyCandy.setCandyName("Toblerone");
        onlyCandy.setCandyPrice(new BigDecimal (2.00));
        onlyCandy.setCandyQuantity(6);
    }
    
    public VendingMachineDaoStubImpl(Candy testCandy){
        this.onlyCandy = testCandy;
    }
    
    @Override
    public List<Candy> getAllCandy() throws VendingMachinePersistenceException {
        List<Candy> candies = new ArrayList<>();
        candies.add(onlyCandy);
        return candies;
    }

    @Override
    public void addMoney(BigDecimal money) throws VendingMachinePersistenceException {
        money = new BigDecimal(0);
    }

    @Override
    public BigDecimal getChangeBalance() throws VendingMachinePersistenceException {
        BigDecimal balance = new BigDecimal(0);
        return balance;
    }

    @Override
    public Candy buyCandy(int candyNumber) throws VendingMachinePersistenceException {
        if(candyNumber == (onlyCandy.getCandyNumber())) {
            return onlyCandy;
        } else {
            return null;
        }
    }

    @Override
    public String getBalanceInCoins() throws VendingMachinePersistenceException {
        return "";
    }
    
}
