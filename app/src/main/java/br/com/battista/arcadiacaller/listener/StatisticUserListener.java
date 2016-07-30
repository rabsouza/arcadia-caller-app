package br.com.battista.arcadiacaller.listener;

import br.com.battista.arcadiacaller.model.enuns.StatisticUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface StatisticUserListener {

    String URI_FIND_BY_USER = "statistic/{username}";

    @GET(URI_FIND_BY_USER)
    Call<StatisticUser> findByUser(@Header("token") String token, @Path("username") String username);

}
