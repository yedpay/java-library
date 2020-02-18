/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

import Yedpay.Response.Response;
import java.util.HashMap;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author kobeleung
 */
public class ApiKeyClientTest {
    private static String API_KEY;
    @InjectMocks
    private static ApiKeyClient client;
    @Mock
    private static HttpTask httpTaskMock;
    
    public ApiKeyClientTest() {
    }
    
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        API_KEY = "123456"; // TODO: API_KEY
        client = new ApiKeyClient("staging", API_KEY);
        httpTaskMock = Mockito.mock(HttpTask.class);
    }
    
    @AfterClass
    public static void tearDownClass() {
        API_KEY = "";
        client = null;
        httpTaskMock = null;
    }

    /**
     * Test of refund method, of class Client.
     */
    @Test
    public void testRefund() {
        String transactionId = "";
        
        when (httpTaskMock.execute(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(HashMap.class))).thenReturn(new Yedpay.Response.Error("0"));
        Response result = client.refund(transactionId);
        
        assertTrue(result instanceof Yedpay.Response.Error);
    }

    /**
     * Test of setCurrency method, of class Client.
     */
    @Test
    public void testSetCurrencyHKD() throws Exception {
        client.setCurrency(Constant.INDEX_CURRENCY_HKD);
        assertEquals(Constant.CURRENCY_HKD, client.getCurrency());
    }

    @Test
    public void testSetCurrencyRMB() throws Exception {
        client.setCurrency(Constant.INDEX_CURRENCY_RMB);
        assertEquals(Constant.CURRENCY_RMB, client.getCurrency());
    }

    @Test(expected = Exception.class)
    public void testSetCurrencyException() throws Exception {
        client.setCurrency(0);
    }

    /**
     * Test of setWallet method, of class Client.
     */
    @Test
    public void testSetWalletHK() throws Exception {
        client.setWallet(Constant.INDEX_WALLET_HK);
        assertEquals(Constant.WALLET_HK, client.getWallet());
    }
    
    @Test
    public void testSetWalletCN() throws Exception {
        client.setWallet(Constant.INDEX_WALLET_CN);
        assertEquals(Constant.WALLET_CN, client.getWallet());
    }
    
    @Test(expected = Exception.class)
    public void testSetWalletException() throws Exception {
        client.setWallet(0);
    }

    /**
     * Test of setGatewayCode method, of class Client.
     */
    @Test
    public void testSetGatewayCodeAlipayOnline() throws Exception {
        client.setGatewayCode(Constant.GATEWAY_CODE_ALIPAY_ONLINE);
        assertEquals(Constant.GATEWAY_CODE_ALIPAY_ONLINE, client.getGatewayCode());
    }

    /**
     * Test of setGatewayCode method, of class Client.
     */
    @Test
    public void testSetGatewayCodeAlipayOnlinePC2Mobile() throws Exception {
        client.setGatewayCode(Constant.GATEWAY_CODE_ALIPAY_ONLINE_PC2MOBILE);
        assertEquals(Constant.GATEWAY_CODE_ALIPAY_ONLINE_PC2MOBILE, client.getGatewayCode());
    }
    
    /**
     * Test of setGatewayCode method, of class Client.
     */
    @Test
    public void testSetGatewayCodeWechatpayOnlnie() throws Exception {
        client.setGatewayCode(Constant.GATEWAY_CODE_WECHATPAY_ONLINE);
        assertEquals(Constant.GATEWAY_CODE_WECHATPAY_ONLINE, client.getGatewayCode());
    }
    
    /**
     * Test of setGatewayCode method, of class Client.
     */
    @Test(expected = Exception.class)
    public void testSetNotSupportedGatewayCodeAlipayOnline() throws Exception {
        client.setGatewayCode("");
    }
     
    @Test
    public void testSetGatewayAlipay() throws Exception {
        client.setGateway(Constant.INDEX_GATEWAY_ALIPAY);
        assertEquals(Constant.INDEX_GATEWAY_ALIPAY, client.getGateway());
    }
     
    @Test
    public void testSetGatewayUnionPay() throws Exception {
        client.setGateway(Constant.INDEX_GATEWAY_UNIONPAY);
        assertEquals(Constant.INDEX_GATEWAY_UNIONPAY, client.getGateway());
    }
     
    @Test
    public void testSetGatewayAlipayOnline() throws Exception {
        client.setGateway(Constant.INDEX_GATEWAY_ALIPAY_ONLINE);
        assertEquals(Constant.INDEX_GATEWAY_ALIPAY_ONLINE, client.getGateway());
    }
    
    public void testSetSubject() throws Exception {
        client.setSubject("Test");
        assertEquals("Test", client.getSubject());
    }
    
    @Test
    public void testSetExpiryTime() throws Exception {
        client.setExpiryTime(9000);
    }

    @Test(expected = Exception.class)
    public void testSetExpiryTimeTooSmall() throws Exception {
        client.setExpiryTime(0);
    }

    @Test(expected = Exception.class)
    public void testSetExpiryTimeTooLarge() throws Exception {
        client.setExpiryTime(999999);
    }

    /**
     * Test of setReturnUrl method, of class Client.
     */
    @Test
    public void testSetReturnUrl() {
        client.setReturnUrl("");
        assertEquals("", client.getReturnUrl());
    }

    /**
     * Test of setNotifyUrl method, of class Client.
     */
    @Test
    public void testSetNotifyUrl() {
        client.setNotifyUrl("");
        assertEquals("", client.getNotifyUrl());
    }
    
    /**
     * Test of setNotifyUrl method, of class Client.
     */
    @Test
    public void testCreateOnlinePayment() throws Exception {
        when (httpTaskMock.execute(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(HashMap.class))).thenReturn(new Yedpay.Response.Error("0"));
        Response result = client.createOnlinePayment("1", 0.1d);
        
        assertTrue(result instanceof Yedpay.Response.Error);
    }
    
    
    @Test
    public void testVerifySign() throws Exception {
       String json =  "{\"c\":3,\"b\":{\"b3\":\"Test\",\"b2\":\"Test\",\"b1\":\"Test\"},\"a\":1,\"sign\":\"6e9175c08e4a25d23bddf20b6681b24502f28b537a1710a67355058d6eacfca0\",\"sign_type\":\"HMAC_SHA256\"}";
       boolean result = ApiKeyClient.verifySign(json, "12345678901234567890123456789012", new String[0]);
       
       assertTrue(result);
    }
    
    @Test
    public void testVerifyFalseSign() throws Exception {
       String json =  "{\"c\":3,\"b\":{\"b3\":\"Test\",\"b2\":\"Test\",\"b1\":\"Test\"},\"a\":1,\"sign\":\"123\",\"sign_type\":\"HMAC_SHA256\"}";
       boolean result = ApiKeyClient.verifySign(json, "12345678901234567890123456789012", new String[0]);
       
       assertTrue(!result);
    }
    
    @Test(expected = Exception.class)
    public void testVerifySIgnWithoutSignature() throws Exception {
       String json =  "{\"c\":3,\"b\":{\"b3\":\"Test\",\"b2\":\"Test\",\"b1\":\"Test\"},\"a\":1,\"sign_type\":\"HMAC_SHA256\"}";
       ApiKeyClient.verifySign(json, "12345678901234567890123456789012", new String[0]);
    }
    
    @Test(expected = Exception.class)
    public void testVerifySIgnWithoutSignType() throws Exception {
       String json =  "{\"c\":3,\"b\":{\"b3\":\"Test\",\"b2\":\"Test\",\"b1\":\"Test\"},\"a\":1,\"sign\":\"123\"}";
       ApiKeyClient.verifySign(json, "12345678901234567890123456789012", new String[0]);
    }
}
