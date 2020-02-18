/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import Yedpay.ApiKeyClient;
import Yedpay.Constant;
import Yedpay.Response.Response;
import Yedpay.Response.Success;

/**
 *
 * @author kobeleung
 */
public class ExampleCreateOnlinePaymentClient {
    public static void main(String[] args) {
        // mandatory parameters
        String API_KEY = ""; //input api key here
        double amount = 0.1d;
        try {
            // Create instance of Client
            ApiKeyClient client = new ApiKeyClient(Constant.STAGING, API_KEY);
            client.setExpiryTime(10800);
            client.setNotifyUrl(""); //input notify url here
            client.setReturnUrl(""); //input redirect url here
            
            
            
            String returnMessage = "";
            // Sending Precreate Request
            Response result = client.createOnlinePayment("kobe_javatest_4", amount);
            if (result instanceof Success) {
                returnMessage = result.getResponseCode() + ":" + ((Success) result).getData();
            } else {
                returnMessage = result.getResponseCode() + ":" + ((Yedpay.Response.Error) result).getErrorMessage();
            }
            
            System.out.println("returnMessage: " + returnMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
