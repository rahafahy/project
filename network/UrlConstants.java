package com.example.lastone.network;

public class UrlConstants {
    //URL  to run on device via LAN..........
    //http://hq-fnweb02/PDAService/api/
    public static String BASE_URL = "http://10.10.20.18/PDAService/api/";
    public static String BASE_URL1 = "http://10.10.20.18/PDAService/api/";
////
//    public static String BASE_URL = "http://64.20.53.171/PDA/api/";
//    public static String BASE_URL1 = "http://64.20.53.171/PDA/api/";
    //URL  to run on local..........
//  public static String BASE_URL = "http://192.168.10.5:23343/api/";
//  public static String BASE_URL1 = "http://192.168.10.5:23343/api/";

    public static int LOGIN_CODE = 0;
    public static int GET_ITEM_BY_BARCODE = 1;
    public static int GET_OTHERS_TYPE = 2;
    public static int GET_DEPARTMENTS = 3;
    public static int GET_MAINSOURSES = 4;
    public static int GET_SUBSOURSES = 5;
    public static int SEND_BATCH = 6;

    public static int GET_PENDING_ITEM_BY_BARCODE = 7;
    public static int DELIVER_BATCH = 8;
    public static int GET_PENDING_ITEM = 9;
    public static int GET_INBOX_BATCHES = 10;
    public static int GET_POLICE_OFFICE = 11;
    public static int CHECK_BULLETIN = 12;
}

