/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import Yedpay.ApiKeyClient;
import Yedpay.Constant;
import Yedpay.Response.Response;
import Yedpay.Response.Error;
import Yedpay.Response.Success;

public class ExampleRefundOnlinePaymentClient {
    
    public static void main(String[] args) {
        // mandatory parameters
        String API_KEY = ""; //Input API Key here
        String customId = ""; //Input custom id here
        
        try {
            // Create instance of Client
            ApiKeyClient client = new ApiKeyClient(Constant.STAGING, API_KEY);
            
            String returnMessage = "";
            // Sending Refund Request
            Response result = client.refundByCustomId(customId, null);
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