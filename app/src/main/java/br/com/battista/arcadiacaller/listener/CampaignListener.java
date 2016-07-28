package br.com.battista.arcadiacaller.listener;

import java.util.List;

import br.com.battista.arcadiacaller.model.Campaign;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CampaignListener {

    String URI_FIND_BY_USER = "campaign/user/{username}";
    String URI_CREATE = "campaign/";
    String URI_UPDATE = "campaign/";
    String URI_DELETE = "campaign/{key}";

    @GET(URI_FIND_BY_USER)
    Call<List<Campaign>> findByUser(@Header("token") String token, @Path("username") String username);

    @POST(URI_CREATE)
    Call<Campaign> create(@Header("token") String token, @Body Campaign campaign);

    @PUT(URI_UPDATE)
    Call<Campaign> update(@Header("token") String token, @Body Campaign campaign);

    @DELETE(URI_DELETE)
    Call<Void> delete(@Header("token") String token, @Path("key") String key);
}
