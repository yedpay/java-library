/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

import Yedpay.Response.Response;
import Yedpay.Response.Error;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;

/**
 *
 * @author Terrie
 */
public abstract class Client {
    private int gateway = 1;
    private String returnUrl = null;
    private String notifyUrl = null;
    private String currency;
    private String wallet;
    private HttpTask httpTask;
    
    // refund
    public Response refund(String transactionId, String refundReason) {
        try {
            String path = String.format(Constant.PATH_REFUND, transactionId);
            HashMap<String, String> parameter = new HashMap<String, String>();
            if (refundReason != null && !refundReason.equals("")) {
                parameter.put("refund_reason",  refundReason);
            }
            
            Response result = httpTask.execute(Constant.POST, path, parameter);
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
    
    public void setHttpTask(HttpTask httpTask) {
        this.httpTask = httpTask;
    }
    
    public HttpTask getHttpTask() {
        return this.httpTask;
    }
    
    public static boolean verifySign(String json, String signKey, String[] unsetFields) throws Exception {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        if (!jsonObject.has("sign") || jsonObject.get("sign").getClass() == JsonNull.class) {
            throw new Exception("No signature detected");
        }
        if (!jsonObject.has("sign_type") || jsonObject.get("sign_type").getClass() == JsonNull.class) {
            throw new Exception("No sign type detected");
        }
        String signature = jsonObject.get("sign").getAsString();
        String signType = jsonObject.get("sign_type").getAsString();
        
        jsonObject.remove("sign");
        jsonObject.remove("sign_type");
        for (int i = 0; i < unsetFields.length; i++) {
            jsonObject.remove(unsetFields[i]);
        }
        
        String httpString = HttpTask.jsonToHttpString(jsonObject);
        
        switch (signType) {
            case "HMAC_SHA256":
                String result = Helper.hmacSha256(httpString, signKey);
                return result.equals(signature.toLowerCase());
        }
        
        return false;
    }

}
