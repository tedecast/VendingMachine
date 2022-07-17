/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

/**
 *
 * @author Teresa
 */
public class Change {
    
    public double calculate(Candy operator, double operand1, double operand2){
        
        switch(operator){
            case TOLBERONE:
                return operand1 - operand2;
            case REESES:
                return operand1 - operand2;
            case KITKAT:
                return operand1 - operand2;
            case PEACHRINGS:
                return operand1 - operand2;
            case JELLYBEANS:
                return operand1 - operand2;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
