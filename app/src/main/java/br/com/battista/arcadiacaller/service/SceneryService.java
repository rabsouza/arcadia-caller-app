package br.com.battista.arcadiacaller.service;


import static br.com.battista.arcadiacaller.listener.SceneryListener.URI_FIND_ALL;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.constants.HttpStatus;
import br.com.battista.arcadiacaller.constants.RestConstant;
import br.com.battista.arcadiacaller.listener.SceneryListener;
import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;
import retrofit2.Response;

public class SceneryService extends BaseService {

    public static final String TAG_CLASSNAME = SceneryService.class.getSimpleName();

    public List<Scenery> findAll(@NonNull String token) {
        Log.i(TAG_CLASSNAME, MessageFormat.format("Find all sceneries in app server url:[{0}]!",
                RestConstant.REST_API_ENDPOINT.concat(URI_FIND_ALL)));

        SceneryListener listener = builder.create(SceneryListener.class);
        List<Scenery> sceneries = Lists.newArrayList();
        try {
            Response<List<Scenery>> response = listener.findAll(token).execute();
            if (response != null && response.code() == HttpStatus.NO_CONTENT.value()) {
                Log.i(TAG, "Found 0 sceneries!");
            } else if (response != null && response.code() == HttpStatus.OK.value() && response.body() != null) {
                sceneries = Lists.newArrayList(response.body());
                Log.i(TAG, MessageFormat.format("Found {0} sceneries!", sceneries.size()));
                return sceneries;
            } else {
                String errorMessage = MessageFormat.format(
                        "Not found sceneries! Return the code status: {0}.",
                        HttpStatus.valueOf(response.code()));
                validateErrorResponse(response, errorMessage);
            }
        } catch (IOException e) {
            Log.e(TAG_CLASSNAME, e.getLocalizedMessage(), e);
        }

        return sceneries;
    }

    public List<Scenery> findByLocation(@NonNull String token, LocationSceneryEnum location) {
        Log.i(TAG_CLASSNAME, MessageFormat.format("Find sceneries by location: {0} in app server url:[{0}]!",
                location.name(), RestConstant.REST_API_ENDPOINT.concat(URI_FIND_ALL)));

        SceneryListener listener = builder.create(SceneryListener.class);
        List<Scenery> sceneries = Lists.newArrayList();
        try {
            Response<List<Scenery>> response = listener.findByLocation(token, location).execute();
            if (response != null && response.code() == HttpStatus.NO_CONTENT.value()) {
                Log.i(TAG, "Found 0 sceneries!");
            } else if (response != null && response.code() == HttpStatus.OK.value() && response.body() != null) {
                sceneries = Lists.newArrayList(response.body());
                Log.i(TAG, MessageFormat.format("Found {0} sceneries!", sceneries.size()));
                return sceneries;
            } else {
                String errorMessage = MessageFormat.format(
                        "Not found sceneries! Return the code status: {0}.",
                        HttpStatus.valueOf(response.code()));
                validateErrorResponse(response, errorMessage);
            }
        } catch (IOException e) {
            Log.e(TAG_CLASSNAME, e.getLocalizedMessage(), e);
        }

        return sceneries;
    }

}
