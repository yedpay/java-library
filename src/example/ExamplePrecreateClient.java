/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import Yedpay.AccessTokenClient;
import Yedpay.Constant;
import Yedpay.Response.Error;
import Yedpay.Response.Response;
import Yedpay.Response.Success;
import java.util.HashMap;

public class ExamplePrecreateClient {
    
    public static void main(String[] args) {
        // mandatory parameters
        String storeId = "Z6YGJP61"; // input store id here 
        float amount = (float) 0.1;
        String ACCESS_TOKEN = "J84OFPAN"; //input access token here
        
        // optional parameter: extraParam (HashMap)
        HashMap<String, String> extraParam = new HashMap();
        extraParam.put("customer_name", "YedPay");
        extraParam.put("phone", "12345678");
        
        try {
            // Create instance of Client
            AccessTokenClient client = new AccessTokenClient(Constant.STAGING, ACCESS_TOKEN);
            
            // (Optional) Setting Transaction parameters
            // changing transaction currency (default: HKD)
            client.setCurrency(Constant.INDEX_CURRENCY_HKD);
            // changing alipay wallet type (default: HK)
            client.setWallet(Constant.INDEX_WALLET_CN);
            // changing gateway (default: ALIPAY)
            client.setGateway(Constant.INDEX_GATEWAY_ALIPAY_ONLINE);
            
            // uncomment if online gateway selected
//            client.setNotifyUrl("");
//            client.setReturnUrl("");
            
            String returnMessage = "";
            // Sending Precreate Request
            Response result = client.precreate(storeId, amount, extraParam);
            if (result instanceof Success) {
                returnMessage = result.getResponseCode() + ":" + ((Success) result).getData();
            } else {
                returnMessage = result.getResponseCode() + ":" + ((Error) result).getErrorMessage();
            }
            
            System.out.println("returnMessage: " + returnMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}