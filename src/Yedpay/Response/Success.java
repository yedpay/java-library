/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay.Response;

import org.json.JSONObject;

/**
 *
 * @author Terrie
 */
public class Success extends Response {
    private JSONObject data;
    
    public Success(String responseCode, JSONObject response) {
        super(responseCode);
        data = response;
    }
    
    public JSONObject getData() {
        return data;
    }
}
