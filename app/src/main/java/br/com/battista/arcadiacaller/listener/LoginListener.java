package br.com.battista.arcadiacaller.listener;

import java.util.Map;

import br.com.battista.arcadiacaller.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by rabsouza on 17/07/16.
 */

public interface LoginListener {

        @GET("login/{username}")
        Call<Map<String, String>> login(@Path("username") String username);

        @POST("login/")
        Call<User> create(@Body User user);
}
