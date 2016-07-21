package br.com.battista.arcadiacaller.listener;

import java.util.List;

import br.com.battista.arcadiacaller.model.Card;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CardListener {

    String URI_FIND_ALL = "card/";

    @GET(URI_FIND_ALL)
    Call<List<Card>> findAll(@Header("token") String token);

}
