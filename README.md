[![Build Status](https://travis-ci.org/yedpay/java-library.svg?branch=master)](https://travis-ci.org/yedpay/java-library)

# Yedpay precreate Library

## Description

A Java library for Precreate Transactions Using the Yedpay API

## Prerequisites
* [Yedpay](https://www.yedpay.com/)

In order to start using the API, you need to get Your Personal Access Token.

## Integration

### Request Method

| Parameter | Type | Description |
| --- | --- | --- |
| accessToken | String | AccessToken used to access API |
| environment | String | Environment ( 'staging' or 'production' )|
| storeId | String | Store ID in the API|
| transactionId | String | Transaction ID in the API|
| amount  | float | amount of the transaction|
| currency  | Integer | transaction currency (1: HKD, 2: RMB)|
| wallet  | Integer | alipay wallet type (1: HK, 2: CN) |
| gateway  | Integer | (1 - Alipay, 2 - unionpay, 4 - Alipay Online) |
| extraParam | HashMap | Extra infomation of the transaction |

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
	float amount = (float) 0.1;
	String ACCESS_TOKEN = "J84OFPAN";
	// optional parameter: extraParam (HashMap)
	HashMap<String, String> extraParam = new HashMap();
	extraParam.put("customer_name", "YedPay");
	extraParam.put("phone", "12345678");

Create instance of Client

    Client client = new Client(Constant.STAGING, ACCESS_TOKEN);
    
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

* Exampe Refund Transaction Implementation

Input parameters
    
	// mandatory parameters
	String ACCESS_TOKEN = "8X4Z6Y4";
	String transactionId = "GPJLL2X";
    

Create instance of Client

    Client client = new Client(Constant.STAGING, ACCESS_TOKEN);
    
Sending Refund Request
    
    Response result = client.refund(transactionId);

For the complete Code Check the examples folder: 
