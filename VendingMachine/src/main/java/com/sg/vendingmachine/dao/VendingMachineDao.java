/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Candy;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface VendingMachineDao {
    
    List<Candy> getAllCandy();
    
    Candy getCandy(String candyNumber);
    
    void purchaseCandy(String candyNumber);
}
