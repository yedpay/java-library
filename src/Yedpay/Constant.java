/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

/**
 *
 * @author Terrie
 */
public final class Constant {
    
    public static final String PRODUCTION = "production";
    public static final String STAGING = "staging";
    
    public static final int INDEX_GATEWAY_ALIPAY = 1;
    public static final int INDEX_GATEWAY_UNIONPAY = 2;
    public static final int INDEX_GATEWAY_ALIPAY_ONLINE = 4;
    
    public static final int INDEX_WALLET_HK = 1;
    public static final int INDEX_WALLET_CN = 2;
    public static final String WALLET_HK = "HK";
    public static final String WALLET_CN = "CN";
    
    public static final int INDEX_CURRENCY_HKD = 1;
    public static final int INDEX_CURRENCY_RMB = 2;
    public static final String CURRENCY_HKD = "HKD";
    public static final String CURRENCY_RMB = "RMB";
    
    public static final String PATH_PRECREATE = "/precreate/%s";
    public static final String PATH_REFUND = "/transactions/%s/refund";
 
    public static final String URL_PRODUCTION = "https://api.yedpay.com";
    public static final String URL_STAGING = "https://api-staging.yedpay.com";
    public static final String API_VERSION = "v1";
    
}