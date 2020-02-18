/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author kobeleung
 */
public class HelperTest {
    
      /**
     * Test of mapToStringException method, of class HttpTask.
     */
    @Test
    public void testByteArrayToHexString() {
        byte[] input = {(byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte)0x90, (byte)0xab, (byte)0xcd, (byte)0xef};
        String result = Helper.bytesToHex(input);
        String expected = "1234567890abcdef";
        
        assertEquals(expected, result);
    }
    
    
    /**
     * Test of mapToStringException method, of class HttpTask.
     */
    @Test
    public void testHmacSha256() throws Exception {
        String result = Helper.hmacSha256("1", "12345678901234567890123456789012");
        String expected = "1e3653a97269df164ee3ac14ffa1d8810f78937e3630cf69842f3b34733cc614";
        
        assertEquals(expected, result);
    }
    /**
     * Test of mapToStringException method, of class HttpTask.
     */
    @Test(expected = Exception.class)
    public void testHmacSha256ShortKey() throws Exception {
        Helper.hmacSha256("1", "123");
    }
}
