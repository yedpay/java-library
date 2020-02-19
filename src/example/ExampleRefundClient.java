/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import Yedpay.AccessTokenClient;
import Yedpay.Constant;
import Yedpay.Response.Response;
import Yedpay.Response.Error;
import Yedpay.Response.Success;

public class ExampleRefundClient {
    
    public static void main(String[] args) {
        // mandatory parameters
        String ACCESS_TOKEN = "8X4Z6Y4"; //Input Access Token here
        String transactionId = "GPJLL2X"; // input  transaction_id here
        
        try {
            // Create instance of Client
            AccessTokenClient client = new AccessTokenClient(Constant.STAGING, ACCESS_TOKEN);
            
            // (Optional) Setting Transaction parameters
            // changing transaction currency (default: HKD)
            client.setCurrency(Constant.INDEX_CURRENCY_HKD);
            // changing alipay wallet type (default: HK)
            client.setWallet(Constant.INDEX_WALLET_CN);
            // changing gateway (default: ALIPAY_ONLINE)
            client.setGateway(Constant.INDEX_GATEWAY_ALIPAY_ONLINE);
            
            String returnMessage = "";
            // Sending Refund Request
            Response result = client.refund(transactionId, null);
            if (result instanceof Success) {
                returnMessage = ((Success) result).getData().toString();
            } else {
                returnMessage = ((Error) result).getErrorMessage();
            }
            
            System.out.println("returnMessage: " + returnMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}