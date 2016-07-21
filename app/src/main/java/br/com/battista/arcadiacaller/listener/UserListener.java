package br.com.battista.arcadiacaller.listener;

import static br.com.battista.arcadiacaller.constants.RestConstant.HEADER_CACHE_CONTROL_MAX_AGE;
import static br.com.battista.arcadiacaller.constants.RestConstant.HEADER_USER_AGENT;

import br.com.battista.arcadiacaller.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UserListener {

    String URI_FIND_USERNAME = "user/{username}";

    @Headers({
            HEADER_CACHE_CONTROL_MAX_AGE,
            HEADER_USER_AGENT
    })
    @GET(URI_FIND_USERNAME)
    Call<User> findByUsername(@Header("token") String token, @Path("username") String username);

}
