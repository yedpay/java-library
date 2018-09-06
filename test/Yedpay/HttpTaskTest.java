/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;

/**
 *
 * @author Terrie
 */
public class HttpTaskTest {
    @InjectMocks
    private static HttpTask httpTask;
    
    public HttpTaskTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        httpTask = new HttpTask("", "");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setAccessToken method, of class HttpTask.
     */
    @Test
    public void testSetAccessToken() {
        httpTask.setAccessToken("123123");
        
        assertEquals("123123", httpTask.getAccessToken());
    }

    /**
     * Test of setEnvironmentProduction method, of class HttpTask.
     */
    @Test
    public void testSetEnvironmentProduction() throws Exception {
        httpTask.setEnvironment(Constant.PRODUCTION);
        
        assertEquals(Constant.PRODUCTION, httpTask.getEnvironment());
    }

    /**
     * Test of setEnvironmentStaging method, of class HttpTask.
     */
    @Test
    public void testSetEnvironmentStaging() throws Exception {
        httpTask.setEnvironment(Constant.STAGING);
        
        assertEquals(Constant.STAGING, httpTask.getEnvironment());
    }

    /**
     * Test of setEnvironmentException method, of class HttpTask.
     */
    @Test(expected = Exception.class)
    public void testSetEnvironmentException() throws Exception {
        httpTask.setEnvironment("exception");
    }

    /**
     * Test of getEndpoint method, of class HttpTask.
     */
    @Test
    public void testGetEndpoint() {
        assertNotNull(httpTask.getEndpoint());
    }

    /**
     * Test of mapToString method, of class HttpTask.
     */
    @Test
    public void testMapToString() {
        Map<String, String> map = new HashMap<>();
        map.put("testkey", "testvalue");
        
        String expResult = "testkey=testvalue";
        String result = HttpTask.mapToString(map);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of mapToStringException method, of class HttpTask.
     */
    @Test(expected = Exception.class)
    public void testMapToStringException() {
        String result = HttpTask.mapToString(null);
    }
}
