/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    public static void setUpClass() throws Exception {
        httpTask = new HttpTask("", "", Constant.INDEX_AUTHENTICATION_ACCESS_TOKEN);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Test of setAuthentication method, of class HttpTask
     * @throws Exception 
     */
    @Test
    public void testSetAuthenticationAccessToken() throws Exception {
        httpTask.setAuthentication(Constant.INDEX_AUTHENTICATION_ACCESS_TOKEN);
        
        assertEquals(Constant.AUTHENTICATION_ACCESS_TOKEN, httpTask.getAuthentication());
    }
    
    /**
     * Test of setAuthentication method, of class HttpTask
     * @throws Exception 
     */
    @Test
    public void testSetAuthenticationApiKey() throws Exception {
        httpTask.setAuthentication(Constant.INDEX_AUTHENTICATION_API_KEY);
        
        assertEquals(Constant.AUTHENTICATION_API_KEY, httpTask.getAuthentication());
    }
    
    
    @Test(expected = Exception.class)
    public void testSetUnknownAuthentication() throws Exception {
        httpTask.setAuthentication(0);
    }

    /**
     * Test of setAccessToken method, of class HttpTask.
     */
    @Test
    public void testSetAccessToken() throws Exception {
        httpTask.setAccessToken("123123");
        
        assertEquals("123123", httpTask.getCredential());
        assertEquals(Constant.AUTHENTICATION_ACCESS_TOKEN, httpTask.getAuthentication());
    }
    
    /**
     * Test of setApiKey method, of class HttpTask.
     */
    @Test
    public void testSetApiKey() throws Exception {
        httpTask.setApiKey("123123");
        
        assertEquals("123123", httpTask.getCredential());
        assertEquals(Constant.AUTHENTICATION_API_KEY, httpTask.getAuthentication());
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
    
    
        /**
     * Test of jsonToHttpString method, of class HttpTask.
     */
    @Test
    public void testJsonToHttpString() {
        String json =  "{\"c\":3,\"b\":{\"b3\":\"Test\",\"b2\":\"Test\",\"b1\":null},\"a\":1,\"d\":true}";
        String expected  = "a=1&b[b3]=Test&b[b2]=Test&c=3&d=1";
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        String result = HttpTask.jsonToHttpString(object);
        
        assertEquals(expected, result);
    }
    
        /**
     * Test of jsonToHttpString method, of class HttpTask.
     */
    @Test
    public void testJsonBooleanToHttpString() {
        String json =  "{\"e\":false,\"d\":true}";
        String expected  = "d=1&e=0";
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        String result = HttpTask.jsonToHttpString(object);
        
        assertEquals(expected, result);
    }
    
        /**
     * Test of jsonToHttpString method, of class HttpTask.
     */
    @Test
    public void testJsonNullToHttpString() {
        String json =  "{\"a\":1,\"d\":null}";
        String expected  = "a=1";
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        String result = HttpTask.jsonToHttpString(object);
        
        assertEquals(expected, result);
    }
}
