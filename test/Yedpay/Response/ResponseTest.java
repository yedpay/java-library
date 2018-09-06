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
public class ResponseTest {
    
    public ResponseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getResponseCode method, of class Response.
     */
    @Test
    public void testGetResponseCode() {
        Response instance = new Response("200");
        
        String expResult = "200";
        String result = instance.getResponseCode();
        
        assertEquals(expResult, result);
    }
    
}
