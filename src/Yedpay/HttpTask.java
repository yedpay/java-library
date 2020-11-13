/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

import Yedpay.Response.Response;
import Yedpay.Response.Success;
import Yedpay.Response.Error;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.json.JSONObject;

/**
 *
 * @author Terrie
 */
public class HttpTask {
    private HttpURLConnection connection;
    private URL url;
    private String credential;
    private String environment = "staging";
    private String authentication;
    
    /**
     * 
     * @param environment
     * @param credential
     * @param authentication
     * @throws Exception 
     */
    public HttpTask(String environment, String credential, int authentication) throws Exception {
        this.environment = environment;
        this.credential = credential;
        this.setAuthentication(authentication);
    }
    
    public Response execute(String method, String url, HashMap parameters) {
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
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", this.getAuthenticationPrefix() + " " + credential);
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
            if (json.has("success")) {
                return new Success(responseCode + "", json);
            } else {
                if (json.has("message")) {
                    return new Error(responseCode + "", json.getString("message"));
                } else {
                    return new Error(responseCode + "", responseMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Error(responseCode + "", e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    /**
     * 
     * @param authentication
     * @throws Exception 
     */
    public void setAuthentication(int authentication) throws Exception {
        switch (authentication) {
            case Constant.INDEX_AUTHENTICATION_ACCESS_TOKEN:
                this.authentication = Constant.AUTHENTICATION_ACCESS_TOKEN;
                break;
            case Constant.INDEX_AUTHENTICATION_API_KEY:
                this.authentication = Constant.AUTHENTICATION_API_KEY;
                break;
            default:
                throw new Exception("Not supported authentication");
        }
    }
    
    /**
     * 
     * @return 
     */
    public String getAuthentication() {
        return this.authentication;
    }

    /**
        * Set the value of accessToken
        * @param accessToken
        */
    public void setAccessToken(String accessToken) throws Exception {
        this.credential = accessToken;
        this.setAuthentication(Constant.INDEX_AUTHENTICATION_ACCESS_TOKEN);
    }
    
    /**
       * Set the value of apiKey
       * @param apiKey 
       */
    public void setApiKey(String apiKey) throws Exception {
        this.credential = apiKey;
        this.setAuthentication(Constant.INDEX_AUTHENTICATION_API_KEY);
    } 
    
    /**
     * 
     * @return 
     */
    public String getCredential() {
        return this.credential;
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
            String value = map.get(key);
            appendStringBuilder(stringBuilder, key , value);
        }

        return stringBuilder.toString();
    }
    
    public static String jsonToHttpString(JsonObject jsonObject) {
        StringBuilder stringBuilder = new StringBuilder();
        
        String key;
        Set<String> keys = new TreeSet<String>(jsonObject.keySet());
       Iterator  it = keys.iterator();
        while (it.hasNext()) {
            key = (String) it.next();
            JsonElement value = jsonObject.get(key);
            if (value.getClass() == JsonObject.class) {
                LinkedHashMap details = new Gson().fromJson(value.toString(), LinkedHashMap.class);
                Iterator subIt = details.keySet().iterator();
                String subKey;
                String urlKey = "";
                JsonElement subValue;
                while (subIt.hasNext()) {
                    subKey = (String) subIt.next();
                    subValue = ((JsonObject)value).get(subKey);
                    urlKey = key == null || subKey == null ?  urlKey : String.format("%s[%s]", key, subKey);
                    appendStringBuilder(stringBuilder, urlKey, subValue);
                }
                continue;
            }
            appendStringBuilder(stringBuilder, key, value);
        }
        return URLDecoder.decode(stringBuilder.toString());
    }
    
    private static StringBuilder appendStringBuilder(StringBuilder stringBuilder, String key, String value) throws RuntimeException {
        if (stringBuilder.length() > 0) {
            stringBuilder.append("&");
        }
        try {
            stringBuilder.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
            stringBuilder.append("=");
            stringBuilder.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");
        } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
        }
        return stringBuilder;
    }
    
    private static StringBuilder appendStringBuilder(StringBuilder stringBuilder, String key, JsonElement value) {
        if (value.getClass() == JsonNull.class) {
            return stringBuilder;
        }
        
        if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isBoolean()) {
            return appendStringBuilder(stringBuilder, key, value.getAsJsonPrimitive().getAsBoolean() ? "1" : "0");
        }
        
        return appendStringBuilder(stringBuilder, key, value.getAsString());
    }

    /**
     * 
     * @return
     * @throws Exception 
     */
    private String getAuthenticationPrefix() throws Exception {
        switch (authentication) {
            case Constant.AUTHENTICATION_ACCESS_TOKEN:
                return "Bearer";
            case Constant.AUTHENTICATION_API_KEY:
                return "API-KEY";
        }
        throw new Exception("Not support authentication");
    }
}
