package br.com.battista.arcadiacaller.service.server;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.constants.HttpStatus;
import br.com.battista.arcadiacaller.constants.RestConstant;
import br.com.battista.arcadiacaller.listener.HeroListener;
import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.service.BaseService;
import br.com.battista.arcadiacaller.service.HeroService;
import retrofit2.Response;

import static br.com.battista.arcadiacaller.listener.HeroListener.URI_FIND_ALL;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class HeroServiceFromServer extends BaseService implements HeroService {

    public static final String TAG_CLASSNAME = HeroServiceFromServer.class.getSimpleName();

    @Override
    public List<Hero> findAll(@NonNull String token) {
        Log.i(TAG_CLASSNAME, MessageFormat.format("Find all heroes in app server url:[{0}]!",
                RestConstant.REST_API_ENDPOINT.concat(URI_FIND_ALL)));

        HeroListener listener = builder.create(HeroListener.class);
        List<Hero> heroes = Lists.newArrayList();
        try {
            Response<List<Hero>> response = listener.findAll(token).execute();
            if (response != null && response.code() == HttpStatus.NO_CONTENT.value()) {
                Log.i(TAG, "Found 0 heroes!");
            } else if (response != null && response.code() == HttpStatus.OK.value() && response.body() != null) {
                heroes = Lists.newArrayList(response.body());
                Log.i(TAG, MessageFormat.format("Found {0} heroes!", heroes.size()));
                return heroes;
            } else {
                String errorMessage = MessageFormat.format(
                        "Not found heroes! Return the code status: {0}.",
                        HttpStatus.valueOf(response.code()));
                validateErrorResponse(response, errorMessage);
            }
        } catch (IOException e) {
            Log.e(TAG_CLASSNAME, e.getLocalizedMessage(), e);
        }

        return heroes;
    }

}
