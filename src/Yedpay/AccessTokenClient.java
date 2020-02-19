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

/**
 *
 * @author Terrie
 */
public class AccessTokenClient extends Client {
   
    public AccessTokenClient() {
    }
    
    public AccessTokenClient(String environment, String accessToken) throws Exception {
        if (environment != null && accessToken != null) {
            try {
                HttpTask httpTask = new HttpTask(
                        environment, 
                        accessToken, 
                        Constant.INDEX_AUTHENTICATION_ACCESS_TOKEN
                );
                this.setHttpTask(httpTask);
            } catch (Exception ex) {
                throw new Exception("Init HttpTask fail");
            }
        }
        this.setWallet(Constant.INDEX_WALLET_HK);
        this.setCurrency(Constant.INDEX_CURRENCY_HKD);
        this.setGateway(Constant.INDEX_GATEWAY_ALIPAY);
    }

    // precreate
    public Response precreate(String storeId, double amount, HashMap<String, String> extraParam) throws Exception {
        HttpTask httpTask = this.getHttpTask();
        if (httpTask == null) {
            throw new Exception("Httptask should not be null");
        }
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

            Response result = httpTask.execute(Constant.POST, path, parameter);
            return result;
        } catch (Exception e) {
            return new Error("0", e.getMessage());
        }
    }
}
