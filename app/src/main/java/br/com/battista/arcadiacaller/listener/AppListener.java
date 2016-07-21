package br.com.battista.arcadiacaller.listener;

import static br.com.battista.arcadiacaller.constants.RestConstant.HEADER_CACHE_CONTROL_MAX_AGE;
import static br.com.battista.arcadiacaller.constants.RestConstant.HEADER_USER_AGENT;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface AppListener {

    String URI_PING = "ping";
    String URI_HEALTH = "health";

    @Headers({
            HEADER_CACHE_CONTROL_MAX_AGE,
            HEADER_USER_AGENT
    })
    @GET(URI_PING)
    Call<Void> ping();

    @Headers({
            HEADER_CACHE_CONTROL_MAX_AGE,
            HEADER_USER_AGENT
    })
    @GET(URI_HEALTH)
    Call<Map<String, Object>> health();
}
