[![Build Status](https://travis-ci.org/yedpay/java-library.svg?branch=master)](https://travis-ci.org/yedpay/java-library)

# Yedpay precreate Library

## Description

A Java library for Precreate Transactions Using the Yedpay API

## Prerequisites
* [Yedpay](https://www.yedpay.com/)

In order to start using the API, you need to get Your Personal Access Token.

## Integration

### Request Method

#### Precreate Transaction
| Parameter | Type | Description |
| --- | --- | --- |
| accessToken | String | AccessToken used to access API |
| environment | String | Environment ( 'staging' or 'production' )|
| storeId | String | Store ID in the API|
| amount  | float | amount of the transaction|
| currency  | Integer | transaction currency (1: HKD)|
| gateway  | Integer | (1 - Alipay, 4 - Alipay Online) |
| wallet  | Integer | alipay wallet type (1: HK, 2: CN) |
| extraParam | HashMap | Extra infomation of the transaction |
| notifyUrl | String | URL receiving notification after the transaction is finished (Alipay Online Only)|
| returnUrl | String | Redirect URL after the payment is finished (Alipay Online Only)|

#### Create Online Payment

| Parameter | Type | Description |
| --- | --- | --- |
| apiKey | String | API Key used to access API |
| environment | String | Environment ( 'staging' or 'production' )|
| customId | String | Transaction ID of the merchant|
| amount  | float | amount of the transaction|
| currency  | Integer | transaction currency (1: HKD)|
| notifyUrl | String | URL receiving notification after the transaction is finished|
| returnUrl | String | Redirect URL after the payment is finished|
| gatewayCdoe  | String | (Optional) "4_1": Alipay Online WAP, "4_2": Alipay Online PC2Mobile,<br/>"8_2": WeChat Pay Online |
| wallet  | Integer | (Optional) alipay wallet type (1: HK, 2: CN) |
| subject | String | (Optional)Subject of the product |
| expiryTime | Integer | (Optional) Time to Live of the online payment (9000 - 10800)|

#### Refund with transaction id

| Parameter | Type | Description |
| --- | --- | --- |
| accessToken | String | AccessToken used to access API |
| environment | String | Environment ( 'staging' or 'production' )|
| transactionID | String | Transaction ID provided by Yedpay|
| reason | String | Refund reason |

#### Refund with custom id

| Parameter | Type | Description |
| --- | --- | --- |
| apiKey | String | API Key used to access API |
| environment | String | Environment ( 'staging' or 'production' )|
| customId | String | Transaction ID of the merchant|
| reason | String | Refund reason |

### Result

* Success

| Parameter | Type | Description |
| --- | --- | --- |
| responseCode | String | Response code |
| data | JSONObject | Data of the response |

* Error

| Parameter | Type | Description |
| --- | --- | --- |
| responseCode | String | Response code |
| errorMessage | String | Response message |

* Exampe Precreate Transaction Implementation

Input parameters
    
	// mandatory parameters
	String storeId = "8X4LZW2X";
	double amount = (double) 0.1;
	String ACCESS_TOKEN = "J84OFPAN";
	// optional parameter: extraParam (HashMap)
	HashMap<String, String> extraParam = new HashMap();
	extraParam.put("customer_name", "YedPay");
	extraParam.put("phone", "12345678");

Create instance of Client

    //Access Token Client
    Client client = new AccessTokenClient(Constant.STAGING, ACCESS_TOKEN);
    
(Optional) Setting Transaction parameters

    // changing transaction currency (default: HKD)
    client.setCurrency(Constant.INDEX_CURRENCY_HKD);
    // changing alipay wallet type (default: HK)
    client.setWallet(Constant.INDEX_WALLET_CN);
    // changing gateway (default: ALIPAY)
    client.setGateway(Constant.INDEX_GATEWAY_ALIPAY_ONLINE);
    
Sending Precreate Request
    
    // general 
    Response result = client.precreate(storeId, amount, null);
    // with extra parameters
    Response result = client.precreate(storeId, amount, extraParam);

* Exampe Create Online Payment Implementation

Input parameters
    
    String API_KEY = "13241235098797"; //input api key here
    String customId = "1"; //input custom id here
    double amount = 0.1d;

Create instance of Client

    //Access Token Client
    Client client = new ApiKeyClient(Constant.STAGING, API_KEY);
    client.setNotifyUrl(""); //input notify url here
    client.setReturnUrl(""); //input redirect url here
    
(Optional) Setting Transaction parameters

    // choose gateway (default show all gateway)
    client.setGatewayCode(Constant.GATEWAY_CODE_ALIPAY_ONLINE_PC2MOBILE);
    // changing alipay online wallet type
    client.setWallet(Constant.INDEX_WALLET_CN);
    // set expiry time of the online payment (default: 10800)
    client.setExpiryTime(9000);
    // set subject of the online payment (default: 10800)
    client.setSubject("Product 1");
    
    
Sending create Online Payment Request
    
    // general 
     Response result = client.createOnlinePayment(customId, amount);


* Exampe Refund Transaction Implementation

Input parameters
    
	// mandatory parameters
	String ACCESS_TOKEN = "8X4Z6Y4";
	String transactionId = "GPJLL2X";
    

Create instance of Client

    Client client = new AccessTokenClient(Constant.STAGING, ACCESS_TOKEN);
    
Sending Refund Request
    
    //general
    Response result = client.refund(transactionId, null);
    //with refund reason
    Response result = client.refund(transactionId, "damaged product");

* Exampe Refund Transaction Implementation

Input parameters
    
	// mandatory parameters
	String API_KEY = "8X4Z6Y4";
	String customId = "GPJLL2X";
    

Create instance of Client

    Client client = new ApiKeyClient(Constant.STAGING, API_KEY);
    
Sending Refund Request
    
    //general
    Response result = client.refundByCustomId(customId, null);

    //with refund reason
    Response result = client.refundByCustomId(customId, "damaged product");

* Exampe Signature Verification Implementation

Input parameters

    //input received json notification here
    String jsonString = "";
    //input sign key here
    String key = ""; 

    String[] exceptions = new String[0];

Verify Sign
    

    boolean result = Client.verifySign(jsonString, key, exceptions);


For the complete Code Check the examples folder: 
