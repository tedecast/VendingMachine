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
    
    List<Candy> getAllCandy() throws VendingMachineDaoException;
    
    // id
    Candy getOneCandy(String candyNumber) throws VendingMachineDaoException;
    
    Candy purchaseCandy(String candyNumber) throws VendingMachineDaoException;
}
