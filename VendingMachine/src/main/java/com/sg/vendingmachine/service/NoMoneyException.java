/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author Teresa
 */
public class NoMoneyException extends Exception {
    
    public NoMoneyException(String message) {
        super(message);
    }
    
    public NoMoneyException(String message, Throwable cause) {
        super(message, cause);
    }
}
