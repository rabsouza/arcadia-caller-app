package br.com.battista.arcadiacaller.listener;

import br.com.battista.arcadiacaller.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UserListener {

    String URI_FIND_USERNAME = "user/{username}";

    String URI_EXISTS_USERNAME = "user/exists/{username}";

    @GET(URI_FIND_USERNAME)
    Call<User> findByUsername(@Header("token") String token, @Path("username") String username);

    @GET(URI_EXISTS_USERNAME)
    Call<Void> existsUsername(@Header("token") String token, @Path("username") String username);

}
