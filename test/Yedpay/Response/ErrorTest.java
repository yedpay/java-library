/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Terrie
 */
public class ErrorTest {
    
    public ErrorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getErrorMessage400 method, of class Error.
     */
    @Test
    public void testGetErrorMessage400() {
        Error instance = new Error("400");
        String expResult = "Insufficient Funds to refund this Transaction";
        String result = instance.getErrorMessage();
        assertEquals(expResult, result);
    }
    

    /**
     * Test of getErrorMessage401 method, of class Error.
     */
    @Test
    public void testGetErrorMessage401() {
        Error instance = new Error("401");
        String expResult = "Unauthorized";
        String result = instance.getErrorMessage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getErrorMessage406 method, of class Error.
     */
    @Test
    public void testGetErrorMessage406() {
        Error instance = new Error("406");
        String expResult = "Gateway not exist";
        String result = instance.getErrorMessage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getErrorMessage422 method, of class Error.
     */
    @Test
    public void testGetErrorMessage422() {
        Error instance = new Error("422");
        String expResult = "Missing data";
        String result = instance.getErrorMessage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getErrorMessageDefault method, of class Error.
     */
    @Test
    public void testGetErrorMessageDefault() {
        Error instance = new Error("0");
        String expResult = "Unknown Error";
        String result = instance.getErrorMessage();
        assertEquals(expResult, result);
    }
}
