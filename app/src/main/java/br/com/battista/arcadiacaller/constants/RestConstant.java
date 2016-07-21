package br.com.battista.arcadiacaller.constants;

public class RestConstant {

    public static final String REST_API_V1 = "api/v1/";
    public static final String REST_API_ENDPOINT = "https://arcadia-caller-dev.appspot.com/";
    public static final String APP_STATUS = "app.status";
    public static final String APP_STATUS_UP = "UP";

    public static final String PRODUCES = "application/json";
    public static final String CONSUMES = "application/json";
    public static final String BODY_ERROR = "cause";
    public static final String DETAIL_ERROR = "error";
    public static final String DETAIL = "detail";
    public static final String HEADER_CACHE_CONTROL_MAX_AGE = "Cache-Control: max-age=640000";
    public static final String HEADER_USER_AGENT = "User-Agent: arcadia-caller-app";


    private RestConstant(){}
}
