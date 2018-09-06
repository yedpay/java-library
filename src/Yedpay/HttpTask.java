/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

import Yedpay.Response.Response;
import Yedpay.Response.Success;
import Yedpay.Response.Error;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author Terrie
 */
public class HttpTask {
    private HttpURLConnection connection;
    private URL url;
    private String accessToken;
    private String environment = "staging";
    
    public HttpTask(String environment, String accessToken) {
        this.environment = environment;
        this.accessToken = accessToken;
    }
    
    public Response execute(String url, HashMap parameters) {
        int responseCode = 0;
        String responseMessage = "";
        try {
            String urlParameters = "";
            if (parameters != null) {
                urlParameters = mapToString(parameters);
            }
            
            url = getEndpoint() + url;
            this.url = new URL(url);
            connection = (HttpURLConnection) this.url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes(StandardCharsets.UTF_8).length));
            
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.getOutputStream().write(urlParameters.getBytes(StandardCharsets.UTF_8));
         
            responseCode = connection.getResponseCode();
            responseMessage = connection.getResponseMessage();
            
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer(); 
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            
            JSONObject json = new JSONObject(response.toString());

            return new Success(responseCode + "", json);
        } catch (Exception e) {
            return new Error(responseCode + "");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    /**
        * Get the value of accessToken
        * @return accessToken
        */
    public String getAccessToken() {
        return accessToken;
    }

    /**
        * Set the value of accessToken
        * @param accessToken
        */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    /**
        * Get the value of environment
        * @return environment
        */
    public String getEnvironment() {
        return this.environment;
    }

    /**
        * Set the value of environment
        * @param environment
        * @throws Exception
        */
    public void setEnvironment(String environment) throws Exception {
        if (!environment.equals(Constant.PRODUCTION) && !environment.equals(Constant.STAGING)) {
            throw new Exception("Invalid Environment");
        }
        this.environment = environment;
    }
    
    /**
        * Get the value of endpoint
        * @return endpoint
        */
    public String getEndpoint() {
        switch (this.environment) {
            case Constant.PRODUCTION:
                return Constant.URL_PRODUCTION + "/" + Constant.API_VERSION;
            case Constant.STAGING:
                return Constant.URL_STAGING + "/" + Constant.API_VERSION;
            default:
                return Constant.URL_STAGING + "/" + Constant.API_VERSION;
        }
    }
    
    public static String mapToString(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : map.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            String value = map.get(key);
            try {
                stringBuilder.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
                stringBuilder.append("=");
                stringBuilder.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }

        return stringBuilder.toString();
    }
}
