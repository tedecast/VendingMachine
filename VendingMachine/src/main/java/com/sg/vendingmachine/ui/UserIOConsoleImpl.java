/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 *
 * @author Teresa
 */
public class UserIOConsoleImpl implements UserIO {
    
    Scanner userInput = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return prompt = userInput.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(userInput.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        int userInt = Integer.parseInt(userInput.nextLine());
        while (userInt < min || userInt > max) {
            System.out.println(prompt);
            userInt = Integer.parseInt(userInput.nextLine());
        } 
        return userInt;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return Double.parseDouble(userInput.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        double userDoub = Double.parseDouble(userInput.nextLine());
        while (userDoub < min || userDoub > max){
            System.out.println(prompt);
            userDoub = Double.parseDouble(userInput.nextLine());
        }
        return userDoub;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return Float.parseFloat(userInput.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        float userFloat = Float.parseFloat(userInput.nextLine());
        while (userFloat < min || userFloat > max) {
            System.out.println(prompt);
            userFloat = Float.parseFloat(userInput.nextLine());
        }
        return userFloat;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return Long.parseLong(userInput.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        long userLong = Long.parseLong(userInput.nextLine());
        while (userLong < min || userLong > max) {
            System.out.println(prompt);
            userLong = Long.parseLong(userInput.nextLine());
        }
        return userLong;
    }
     //This implementation deals with money
    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        boolean isValid;
        BigDecimal result = null;
        BigDecimal bdMin = (min).setScale(2, RoundingMode.HALF_UP);
        BigDecimal bdMax = (max).setScale(2, RoundingMode.HALF_UP);        
        
        do {
            isValid = true;
            print(prompt + " Between $" + bdMin + " And $" + bdMax);
            try {
                //result = new BigDecimal(scan.nextLine()).setScale(2, RoundingMode.HALF_UP);
                if (result.compareTo(min) == -1 || result.compareTo(max) == 1) {
                    //print("Enter Amount Between $" + bdMin + " And $" + bdMax);
                    isValid = false;
                }
            } catch (NumberFormatException ex) {
                //print("Please Enter Number:");
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean isValid;
        BigDecimal result = null;
        do {
            isValid = true;
            print(prompt);
            try {
                //result = new BigDecimal(scan.nextLine()).setScale(2, RoundingMode.HALF_UP);
            } catch (NumberFormatException ex) {
                //print("Please Enter Number:");
                isValid = false;
            }
        } while (!isValid);
        return result;
    }
}
