/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import Yedpay.Client;

/**
 *
 * @author kobeleung
 */
public class ExampleSignatureVerification {
    public static void main(String[] args) {
        String jsonString = ""; //input received json notification here
        String key = ""; //input sign key here
        String[] exceptions = new String[0];
        try {
            boolean result = Client.verifySign(jsonString, key, exceptions);
            System.out.println(result ? "success" : "fail");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
