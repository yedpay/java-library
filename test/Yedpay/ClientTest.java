/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

import Yedpay.Response.Response;
import Yedpay.Response.Error;
import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Terrie
 */
public class ClientTest {
    private static String ACCESS_TOKEN;
    @InjectMocks
    private static Client client;
    @Mock
    private static HttpTask httpTaskMock;
    
    public ClientTest() {
    }
    
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void setUpClass() {
        ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjkzN2ZjMWNhYWVmNTcyZTM1N2Q5ODQ0YmIxNzNjODYzOGRjZWY3MjM3YTEwOTc3NmZiOGZhMmZkZmRhOTZmMDhhOGZjZDFmNWY3NDlhNDA1In0.eyJhdWQiOiIxIiwianRpIjoiOTM3ZmMxY2FhZWY1NzJlMzU3ZDk4NDRiYjE3M2M4NjM4ZGNlZjcyMzdhMTA5Nzc2ZmI4ZmEyZmRmZGE5NmYwOGE4ZmNkMWY1Zjc0OWE0MDUiLCJpYXQiOjE1MzU0MjQwMTYsIm5iZiI6MTUzNTQyNDAxNiwiZXhwIjoxNTQzMjAwMDE2LCJzdWIiOiI0MTAiLCJzY29wZXMiOltdfQ.hs7pZM59zOg6S9ComhWTsmir_EpXX76iwvBWjAWasxCANtxvn8m5LXDOIOabUQd6K98pC_49UJbMEA9x6EIyT1DPc5Tt8sepcSOneAD1eH375IfSZr287ujrWEHY9oZEZmYvdpQl0c44G6f4v8XaV60_paDWsnHh9wuigAB1A8hZoZhLXFVbvOT7hgAz13tDxCJfVLTdgUymJdk7YpFkUejaQ9_M2fiKwceLwTrJ338D_HVjOArZmg_ZkbssWBVm1nhpAUoqJsUHTBuOqoFq9Dc5Nmzv2rV-oAgh50AlN4OHgs90yhswpGE_1csrk-JqZolrbuDbo6so0tYdKdHgX6E0CVyxchjPHSVotX0C7nCxToEuqM6GTtjjuNJAN7j6bWHwvVVGFx6IbV-IJO45RxZKkM4gA-Qggnii1o_FIu-M2ZUYBZOaXfQWuYQW5gWzLqPMm3Tus0P_2ddq0iNZbQ37iCbuEavgqsiXOl9lGwNLxcHD_CIguMArCu8YudJpZnyxq2Lkm8W9taKCnekCrYQcxawoqyEYBq7oUwbEC8cO5sz5DY-sOUgoXaEULxvJdUoRUFv3O-Pe5rp478_SxJlDUhY1SuMMvy6TNXOsFPmBbmDq5RG5SCzGNMPzsKjoQREPSCTuPCPpA7LYEWAA8kwjfr3oVmWr1277VcaSFsE";
        client = new Client("staging", ACCESS_TOKEN);
        httpTaskMock = Mockito.mock(HttpTask.class);
    }
    
    @AfterClass
    public static void tearDownClass() {
        ACCESS_TOKEN = "";
        client = null;
        httpTaskMock = null;
    }
    
    /**
     * Test of precreate method, of class Client.
     */
    @Test
    public void testPrecreate() {
        String storeId = "123";
        float amount = 0.0F;
        HashMap<String, String> extraParam = new HashMap();
        extraParam.put("customer_name", "YedPay");
        extraParam.put("phone", "1234567890");
        
        when (httpTaskMock.execute(Mockito.any(String.class), Mockito.any(HashMap.class))).thenReturn(new Error("0"));
        Response result = client.precreate(storeId, amount, extraParam);
        
        assertTrue(result instanceof Error);
    }

    /**
     * Test of refund method, of class Client.
     */
    @Test
    public void testRefund() {
        String transactionId = "";
        
        when (httpTaskMock.execute(Mockito.any(String.class), Mockito.any(HashMap.class))).thenReturn(new Error("0"));
        Response result = client.refund(transactionId);
        
        assertTrue(result instanceof Error);
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
     * Test of setGateway method, of class Client.
     */
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
}
