package br.com.battista.arcadiacaller.listener;

import static br.com.battista.arcadiacaller.constants.RestConstant.HEADER_CACHE_CONTROL_MAX_AGE;
import static br.com.battista.arcadiacaller.constants.RestConstant.HEADER_USER_AGENT;

import java.util.List;

import br.com.battista.arcadiacaller.model.Hero;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface HeroListener {

    String URI_FIND_ALL = "hero/";

    @Headers({
            HEADER_CACHE_CONTROL_MAX_AGE,
            HEADER_USER_AGENT
    })
    @GET(URI_FIND_ALL)
    Call<List<Hero>> findAll(@Header("token") String token);

}
