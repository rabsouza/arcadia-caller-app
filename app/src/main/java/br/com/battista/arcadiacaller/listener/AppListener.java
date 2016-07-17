package br.com.battista.arcadiacaller.listener;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rabsouza on 17/07/16.
 */

public interface AppListener {

        @GET("ping")
        Call<Void> ping();

        @GET("health")
        Call<Map<String, Object>> health();
}
