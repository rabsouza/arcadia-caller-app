package br.com.battista.arcadiacaller.listener;

import java.util.List;

import br.com.battista.arcadiacaller.model.Scenery;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SceneryListener {

    String URI_FIND_ALL = "scenery/";

    @GET(URI_FIND_ALL)
    Call<List<Scenery>> findAll(@Header("token") String token);

}
