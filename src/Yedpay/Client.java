/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

import Yedpay.Response.Response;
import Yedpay.Response.Error;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Terrie
 */
public class Client {
    private int gateway = 1;
    private String returnUrl = null;
    private String notifyUrl = null;
    private String currency;
    private String wallet;
    private HttpTask httpTask;
   
    public Client() {
    }
    
    public Client(String environment, String accessToken) throws Exception {
        if (environment != null && accessToken != null) {
            try {
                httpTask = new HttpTask(environment, accessToken);
            } catch (Exception ex) {
                throw new Exception("Init HttpTask fail");
            }
        }
        this.wallet = Constant.WALLET_HK;
        this.currency = Constant.CURRENCY_HKD;
        this.gateway = Constant.INDEX_GATEWAY_ALIPAY;
    }

    // precreate
    public Response precreate(String storeId, float amount, HashMap<String, String> extraParam) {
        String path = String.format(Constant.PATH_PRECREATE, storeId);
        try {
            HashMap<String, String> parameter = new HashMap<>();
            parameter.put("gateway_id", getGateway() + "");
            parameter.put("currency", getCurrency());
            parameter.put("wallet", getWallet());
            parameter.put("amount", amount + "");

            if (extraParam != null) {
                for (Map.Entry<String, String> data : extraParam.entrySet()) {
                    parameter.put(data.getKey(), data.getValue());
                }
            }
            if (this.getNotifyUrl() != null) parameter.put("notify_url", this.getNotifyUrl());
            if (this.getReturnUrl() != null) parameter.put("return_url", this.getReturnUrl());

            Response result = httpTask.execute(path, parameter);
            return result;
        } catch (Exception e) {
            return new Error("0", e.getMessage());
        }
    }
    
    // refund
    public Response refund(String transactionId) {
        try {
            String path = String.format(Constant.PATH_REFUND, transactionId);

            Response result = httpTask.execute(path, null);
            return result;
        } catch (Exception e) {
            return new Error("0", e.getMessage());
        }
    }
    
    public void setCurrency(int currency) throws Exception {
        switch (currency) {
            case Constant.INDEX_CURRENCY_HKD:
                this.currency = Constant.CURRENCY_HKD;
                break;
            case Constant.INDEX_CURRENCY_RMB:
                this.currency = Constant.CURRENCY_RMB;
                break;
            default:
                throw new Exception("Currency not support yet");
        }
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public void setWallet(int wallet) throws Exception {
        switch (wallet) {
            case Constant.INDEX_WALLET_HK:
                this.wallet = Constant.WALLET_HK;
                break;
            case Constant.INDEX_WALLET_CN:
                this.wallet = Constant.WALLET_CN;
                break;
            default:
                throw new Exception("This is not a valid wallet");
        }
    }
    
    public String getWallet() {
        return this.wallet;
    }
    
    public void setGateway(int gateway) throws Exception {
        switch (gateway) {
            case Constant.INDEX_GATEWAY_ALIPAY:
                this.gateway = gateway;
                break;
            case Constant.INDEX_GATEWAY_UNIONPAY:
                this.gateway = gateway;
                break;
            case Constant.INDEX_GATEWAY_ALIPAY_ONLINE:
                this.gateway = gateway;
                break;
            default:
                throw new Exception("Gateway not supported yet");
        }
    }
    
    public int getGateway() {
        return this.gateway;
    }
    
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    
    public String getReturnUrl() {
        return this.returnUrl;
    }
    
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
    
    public String getNotifyUrl() {
        return this.notifyUrl;
    }
}
