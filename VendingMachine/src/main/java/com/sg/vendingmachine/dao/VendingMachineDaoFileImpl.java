/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Candy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Teresa
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    
    private Map<String, Candy> candies = new HashMap<>();

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
    
}
