package br.com.battista.arcadiacaller.service;

import br.com.battista.arcadiacaller.constants.RestConstant;
import lombok.AccessLevel;
import lombok.Getter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class BaseService {

    @Getter(value = AccessLevel.PROTECTED)
    protected Retrofit builder;

    public BaseService() {
        builder = new Retrofit.Builder()
                .baseUrl(RestConstant.REST_API_ENDPOINT.concat(RestConstant.REST_API_V1))
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}
