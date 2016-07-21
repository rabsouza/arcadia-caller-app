package br.com.battista.arcadiacaller.service;


import static br.com.battista.arcadiacaller.listener.CardListener.URI_FIND_ALL;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.constants.HttpStatus;
import br.com.battista.arcadiacaller.constants.RestConstant;
import br.com.battista.arcadiacaller.listener.CardListener;
import br.com.battista.arcadiacaller.model.Card;
import retrofit2.Response;

public class CardService extends BaseService {

    public static final String TAG_CLASSNAME = CardService.class.getSimpleName();

    public List<Card> findAll(@NonNull String token) {
        Log.i(TAG_CLASSNAME, MessageFormat.format("Find all cards in app server url:[{1}]!",
                RestConstant.REST_API_ENDPOINT.concat(URI_FIND_ALL)));

        CardListener listener = builder.create(CardListener.class);
        List<Card> cards = Lists.newArrayList();
        try {
            Response<List<Card>> response = listener.findAll(token).execute();
            if (response != null && response.code() == HttpStatus.NO_CONTENT.value()) {
                Log.i(TAG, "Found 0 cards!");
            } else if (response != null && response.code() == HttpStatus.OK.value() && response.body() != null) {
                cards = Lists.newArrayList(response.body());
                Log.i(TAG, MessageFormat.format("Found {0} cards!", cards.size()));
                return cards;
            } else {
                String errorMessage = MessageFormat.format(
                        "Not found cards! Return the code status: {0}.",
                        HttpStatus.valueOf(response.code()));
                validateErrorResponse(response, errorMessage);
            }
        } catch (IOException e) {
            Log.e(TAG_CLASSNAME, e.getLocalizedMessage(), e);
        }

        return cards;
    }

}