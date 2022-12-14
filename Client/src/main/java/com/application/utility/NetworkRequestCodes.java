package com.application.utility;

public class NetworkRequestCodes {

    public static final int UNKNOWN_ERROR = 900;
    public static final int SERVER_ERROR = 901;
    public static final int DATABASE_SERVER_ERROR = 902;
    public static final int GENERAL_ERROR = 903;


    public static final int SIGN_IN_REQUEST = 101;
    public static final int SIGN_UP_REQUEST = 102;
    public static final int SIGN_OUT_REQUEST = 103;


    public static final int SIGN_IN_SUCCESSFUL = 201;
    public static final int SIGN_IN_UNSUCCESSFUL = -201;
    public static final int SIGN_UP_SUCCESSFUL = 202;
    public static final int SIGN_UP_UNSUCCESSFUL = -202;
    public static final int SIGN_OUT_SUCCESSFUL = 203;
    public static final int SIGN_OUT_UNSUCCESSFUL = -203;


    public static final int CREATE_EVENT_REQUEST = 104;
    public static final int DELETE_EVENT_REQUEST = 105;
    public static final int UPDATE_EVENT_REQUEST = 106;
    public static final int SEARCH_EVENT_REQUEST = 107;


    public static final int CREATE_EVENT_SUCCESSFUL = 204;
    public static final int CREATE_EVENT_UNSUCCESSFUL = -204;
    public static final int DELETE_EVENT_SUCCESSFUL = 205;
    public static final int DELETE_EVENT_UNSUCCESSFUL = -205;
    public static final int UPDATE_EVENT_SUCCESSFUL = 206;
    public static final int UPDATE_EVENT_UNSUCCESSFUL = -206;
    public static final int SEARCH_EVENT_SUCCESSFUL = 207;
    public static final int SEARCH_EVENT_UNSUCCESSFUL = -207;


}
