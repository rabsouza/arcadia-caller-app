package br.com.battista.arcadiacaller.listener;

import static br.com.battista.arcadiacaller.constants.RestConstant.HEADER_CACHE_CONTROL_MAX_AGE;
import static br.com.battista.arcadiacaller.constants.RestConstant.HEADER_USER_AGENT;

import java.util.Map;

import br.com.battista.arcadiacaller.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginListener {

    String URI_LOGIN = "login/{username}";
    String URI_CREATE = "login/";

    @Headers({
            HEADER_CACHE_CONTROL_MAX_AGE,
            HEADER_USER_AGENT
    })
    @GET(URI_LOGIN)
    Call<Map<String, String>> login(@Path("username") String username);

    @POST(URI_CREATE)
    Call<User> create(@Body User user);
}
