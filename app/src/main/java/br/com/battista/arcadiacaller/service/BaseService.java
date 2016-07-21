package br.com.battista.arcadiacaller.service;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

import android.util.Log;

import br.com.battista.arcadiacaller.constants.HttpStatus;
import br.com.battista.arcadiacaller.constants.RestConstant;
import br.com.battista.arcadiacaller.exception.AuthenticationException;
import br.com.battista.arcadiacaller.exception.EntityAlreadyExistsException;
import br.com.battista.arcadiacaller.exception.EntityNotFoundException;
import br.com.battista.arcadiacaller.exception.ValidatorException;
import lombok.AccessLevel;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class BaseService {

    @Getter(value = AccessLevel.PROTECTED)
    protected Retrofit builder;

    public BaseService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        builder = new Retrofit.Builder()
                .baseUrl(RestConstant.REST_API_ENDPOINT.concat(RestConstant.REST_API_V1))
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    protected void validateErrorResponse(Response<?> response, String errorMessage) {
        if (response != null && response.code() == HttpStatus.BAD_REQUEST.value()) {
            Log.e(TAG, errorMessage);
            throw new ValidatorException(errorMessage);
        } else if (response != null && response.code() == HttpStatus.UNAUTHORIZED.value()) {
            Log.e(TAG, errorMessage);
            throw new AuthenticationException(errorMessage);
        } else if (response != null && response.code() == HttpStatus.NOT_FOUND.value()) {
            Log.e(TAG, errorMessage);
            throw new EntityNotFoundException(errorMessage);
        } else if (response != null && response.code() == HttpStatus.CONFLICT.value()) {
            Log.e(TAG, errorMessage);
            throw new EntityAlreadyExistsException(errorMessage);
        } else {
            Log.e(TAG, errorMessage);
        }
    }
}
