package br.com.battista.arcadiacaller.listener;

import br.com.battista.arcadiacaller.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserListener {

    String URI_FIND_USERNAME = "user/{username}";

    String URI_EXISTS_USERNAME = "user/exists/{username}";

    String URI_UPDATE_FRIENDS = "user/friends/";

    String URI_CREATE = "user/";

    @GET(URI_FIND_USERNAME)
    Call<User> findByUsername(@Header("token") String token, @Path("username") String username);

    @GET(URI_EXISTS_USERNAME)
    Call<Void> existsUsername(@Header("token") String token, @Path("username") String username);

    @PUT(URI_UPDATE_FRIENDS)
    Call<User> addFriends(@Header("token") String token, @Body User user);

    @POST(URI_CREATE)
    Call<User> create(@Header("token") String token, @Body User user);

}
