package br.com.battista.arcadiacaller.listener;

import br.com.battista.arcadiacaller.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rabsouza on 17/07/16.
 */

public interface UserListener {

        @GET("user/{username}")
        Call<User> findByUsername(@Path("username") String username);

}
