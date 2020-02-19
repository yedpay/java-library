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
        double amount = (double) 0.1;
        String ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjdlMWVjMWQxN2U0YjRlMmUyMDA4OTY5Nzc4Nzk3ZDNlODIyYzMxNGIwZDI2NDU1MmMxYWZhMDQ3NDc3MGMwYzU1MjYxZDFlNmFlZGU2MGY4In0.eyJhdWQiOiIxIiwianRpIjoiN2UxZWMxZDE3ZTRiNGUyZTIwMDg5Njk3Nzg3OTdkM2U4MjJjMzE0YjBkMjY0NTUyYzFhZmEwNDc0NzcwYzBjNTUyNjFkMWU2YWVkZTYwZjgiLCJpYXQiOjE1ODEzMTY1MTMsIm5iZiI6MTU4MTMxNjUxMywiZXhwIjoxNTg5MDkyNTEzLCJzdWIiOiI2MTkiLCJzY29wZXMiOltdfQ.mSH1cpvK7Dpk9z6zbqNf_XkmQPth1iaJxwOvpdj3pYGd0VasdmFXVA7BwNNp0VcxU2e8wGJsntl7NgkavgcKZNeuAMur20X2mjVkbjhqvAARBztBeS-tJq_n0H2JqQyTmfqVV_2obubp2wvPSoIhOPjq6xvyNOUpRVgWyQcD78sSmkFr1n-6t28_kXedZGVTEZzmk0pfrbBH1ZUuO_Vd9BCqI1NcQsmZO8Vh68mYagsmGvJ4trBiey5hItbb8slGBUfbNkHYnqKCIkT1f499Jib1U_A4gdW0d43PKKg8pgEK-orv1r2QnTxBxAgHGth71ZIeYPqLJRxeEWj7UGDQ_UdJwZozEdL9N1GPJBl8G2IPc54mfy6WHs8xRvgU-nEZNgRr65ihiLcH1iveKRlPUr9hYmqUCN999BAxbT6oBAsH08jUSDMSS8Nm32XLkcl120dhQbouzC8dwfUZFKRi6eLIrSo_xlfj-hUBQKbdqoKUp01rkbQeav17DsjVHFoZqt-d_Ns1lQp6xqbcyymBKUJqYFinW5yD71UDRdw8g1izFUm39EoxCzEgspNW_7yx4QexzC2b16hGUvPbwzX49oX6g010lljFBdSmo57K1PaloOwpfq-NuuD29H8jYT6u99FmuwogyzNyX6ykA9lgvQbuJJhYi9HOnIpFEvSdeqY"; //input access token here
        
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
            client.setGateway(Constant.INDEX_GATEWAY_ALIPAY);
            
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