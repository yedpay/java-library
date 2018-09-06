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
public class Error extends Response {
    private String errorMessage;
    
    /**
        * Error constructor.
        * @param responseCode
        */
    public Error(String responseCode) {
        super(responseCode);
        switch (responseCode) {
            case "400":
                errorMessage = "Insufficient Funds to refund this Transaction";
                break;
            case "401":
                errorMessage = "Unauthorized";
                break;
            case "406":
                errorMessage = "Gateway not exist";
                break;
            case "422":
                errorMessage = "Missing data";
                break;
            default:
                errorMessage = "Unknown Error";
                break;
        }
    }
    
    public Error(String responseCode, String responseMessage) {
        super(responseCode);
        errorMessage = responseMessage;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}
