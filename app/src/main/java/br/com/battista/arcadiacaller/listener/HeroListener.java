package br.com.battista.arcadiacaller.listener;

import java.util.List;

import br.com.battista.arcadiacaller.model.Hero;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HeroListener {

    String URI_FIND_ALL = "hero/";

    @GET(URI_FIND_ALL)
    Call<List<Hero>> findAll(@Header("token") String token);

}
