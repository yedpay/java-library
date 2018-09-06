/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay.Response;

/**
 *
 * @author Terrie
 */
public class Response {
    private String responseCode;
    
    /**
        * Response constructor.
        * @param responseCode
        */
    public Response(String responseCode) {
        this.responseCode = responseCode;
    }
    
    /**
        * @return responseCode
        */ 
    public String getResponseCode()
    {
        return this.responseCode;
    }
}
