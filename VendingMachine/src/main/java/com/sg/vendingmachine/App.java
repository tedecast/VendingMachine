/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;

/**
 *
 * @author Teresa
 */
public class App {
    
    public static void main (String[] args) {
        
        VendingMachineController controller = new VendingMachineController();
        controller.run();
    }
}
