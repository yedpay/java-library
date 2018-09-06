/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay.Response;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Terrie
 */
public class SuccessTest {
    
    public SuccessTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getData method, of class Success.
     */
    @Test
    public void testGetData() {
        Success instance = new Success("200", new JSONObject());
        
        JSONObject result = instance.getData();
        assertTrue(result instanceof JSONObject);
    }
}
