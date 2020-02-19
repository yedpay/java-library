/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

import Yedpay.Response.Response;
import Yedpay.Response.Error;
import java.util.HashMap;

/**
 *
 * @author kobeleung
 */
public class ApiKeyClient extends Client {
    
    private String gatewayCode = null;
    private String subject = null;
    private int expiryTime = 0;
    
    public ApiKeyClient() {
        
    }
    
    /**
     * 
     * @param environment
     * @param apiKey
     * @throws Exception 
     */
    public ApiKeyClient(String environment, String apiKey) throws Exception {
        if (environment != null && apiKey != null) {
            try {
                HttpTask httpTask = new HttpTask(environment, apiKey, Constant.INDEX_AUTHENTICATION_API_KEY);
                this.setHttpTask(httpTask);
            } catch (Exception ex) {
                throw new Exception("Init HttpTask fail");
            }
            this.setCurrency(Constant.INDEX_CURRENCY_HKD);
        }
    }
    
    /**
     * 
     * @param customId
     * @param amount
     * @return
     * @throws Exception 
     */
    public Response createOnlinePayment(String customId, double amount) throws Exception {
        HttpTask httpTask = this.getHttpTask();
        if (httpTask == null) {
            throw new Exception("Httptask should not be null");
        }
        String path = Constant.PATH_ONLINE_PAYMENT_CREATE;
        try {
            HashMap<String, String> parameter = new HashMap<>();
            parameter.put("amount", amount + "");
            parameter.put("currency", getCurrency());
            parameter.put("notify_url", getNotifyUrl());
            parameter.put("return_url", getReturnUrl());
            parameter.put("custom_id", customId);
            if (getSubject() != null) {
                parameter.put("subject", getSubject());
            }
            if (getWallet() != null) {
                parameter.put("wallet", getWallet());
            }
            if (expiryTime > 0) {
                parameter.put("expiry_time", expiryTime + "");
            }
            if (gatewayCode != null) {
                parameter.put("gateway_code", gatewayCode);
            }
            Response response = httpTask.execute(Constant.POST, path, parameter);
            return response;
        } catch (Exception e) {
            return new Error("0", e.getMessage());
        }
    }
    
    public Response refundByCustomId(String customId, String refundReason) throws Exception {
        HttpTask httpTask = this.getHttpTask();
        if (httpTask == null) {
            throw new Exception("Httptask should not be null");
        }
        String path = String.format(Constant.PATH_ONLINE_PAYMENT_REFUND, customId);
        try {
            HashMap<String, String> parameter = new HashMap<>();
            if (refundReason != null && !refundReason.equals("")) {
                parameter.put("refund_reason",  refundReason);
            }
            Response response = httpTask.execute(Constant.PUT, path, parameter);
            return response;
        } catch (Exception e) {
            return new Error("0", e.getMessage());
        }
    }
    
    /**
     * 
     * @param subject 
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     * 
     * @return subject
     */
    public String getSubject() {
        return this.subject;
    }
    
    /**
     * 
     * @param expiryTime
     * @throws Exception 
     */
    public void setExpiryTime(int expiryTime) throws Exception {
        if (expiryTime < 9000 || expiryTime > 10800) {
            throw new Exception("Invalid expiry time value");
        }
        this.expiryTime = expiryTime;
    }
    
    /**
     * 
     * @return expiryTime
     */
    public int getExpiryTime() {
        return this.expiryTime;
    }
    
    /**
     * 
     * @param gatewayCode
     * @throws Exception 
     */
    public void setGatewayCode(String gatewayCode) throws Exception {
        if (!Constant.AVAILABLE_GATEWAY_CODE_LIST.contains(gatewayCode)) {
            throw new Exception("Invalid gateway code");
        }
        this.gatewayCode = gatewayCode;
    }

    /**
     * 
     * @return gatewayCode
     */
    public String getGatewayCode() {
        return gatewayCode;
    }
            
}