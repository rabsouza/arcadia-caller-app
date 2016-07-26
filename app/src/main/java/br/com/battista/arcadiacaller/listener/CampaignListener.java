package br.com.battista.arcadiacaller.listener;

import java.util.List;

import br.com.battista.arcadiacaller.model.Campaign;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface CampaignListener {

    String URI_FIND_BY_USER = "campaign/user/{username}";

    @GET(URI_FIND_BY_USER)
    Call<List<Campaign>> findByUser(@Header("token") String token, @Path("username") String username);

}
