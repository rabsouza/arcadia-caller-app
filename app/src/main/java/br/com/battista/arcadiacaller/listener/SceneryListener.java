package br.com.battista.arcadiacaller.listener;

import java.util.List;

import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface SceneryListener {

    String URI_FIND_ALL = "scenery/";
    String URI_FIND_BY_LOCATION = "scenery/location/{location}";

    @GET(URI_FIND_ALL)
    Call<List<Scenery>> findAll(@Header("token") String token);

    @GET(URI_FIND_BY_LOCATION)
    Call<List<Scenery>> findByLocation(@Header("token") String token, @Path("location") LocationSceneryEnum loacation);

}
