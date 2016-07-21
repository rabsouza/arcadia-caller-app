package br.com.battista.arcadiacaller.listener;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppListener {

    String URI_PING = "ping";
    String URI_HEALTH = "health";

    @GET(URI_PING)
    Call<Void> ping();

    @GET(URI_HEALTH)
    Call<Map<String, Object>> health();
}
