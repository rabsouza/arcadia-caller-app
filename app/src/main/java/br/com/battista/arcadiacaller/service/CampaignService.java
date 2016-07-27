package br.com.battista.arcadiacaller.service;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.constants.HttpStatus;
import br.com.battista.arcadiacaller.constants.RestConstant;
import br.com.battista.arcadiacaller.exception.ValidatorException;
import br.com.battista.arcadiacaller.listener.CampaignListener;
import br.com.battista.arcadiacaller.model.Campaign;
import retrofit2.Response;

import static br.com.battista.arcadiacaller.listener.CampaignListener.URI_FIND_BY_USER;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class CampaignService extends BaseService {

    public static final String TAG_CLASSNAME = CampaignService.class.getSimpleName();

    public List<Campaign> findByUser(@NonNull String token, @NonNull String username) {
        Log.i(TAG_CLASSNAME, MessageFormat.format("Find campaigns by username: {0} in app server url:[{1}]!",
                username, RestConstant.REST_API_ENDPOINT.concat(URI_FIND_BY_USER)));

        CampaignListener listener = builder.create(CampaignListener.class);
        List<Campaign> campaigns = Lists.newArrayList();
        try {
            Response<List<Campaign>> response = listener.findByUser(token, username).execute();
            if (response != null && response.code() == HttpStatus.NO_CONTENT.value()) {
                Log.i(TAG, "Found 0 campaigns!");
            } else if (response != null && response.code() == HttpStatus.OK.value() && response.body() != null) {
                campaigns = Lists.newArrayList(response.body());
                Log.i(TAG, MessageFormat.format("Found {0} campaigns!", campaigns.size()));
                return campaigns;
            } else {
                String errorMessage = MessageFormat.format(
                        "Not found campaigns! Return the code status: {0}.",
                        HttpStatus.valueOf(response.code()));
                validateErrorResponse(response, errorMessage);
            }
        } catch (IOException e) {
            Log.e(TAG_CLASSNAME, e.getLocalizedMessage(), e);
        }

        return campaigns;
    }

    public Campaign create(@NonNull String token, @NonNull Campaign campaign) {
        if (campaign == null) {
            throw new ValidatorException("Campaign can not be null!");
        }

        Log.i(TAG_CLASSNAME, MessageFormat.format("Create new campaign with alias: {0} in app server url:[{1}]!",
                campaign.getAlias(), RestConstant.REST_API_ENDPOINT.concat(URI_FIND_BY_USER)));

        CampaignListener listener = builder.create(CampaignListener.class);
        try {
            Response<Campaign> response = listener.create(token, campaign).execute();
            if (response != null && response.code() == HttpStatus.CREATED.value() && response.body() != null) {
                campaign = response.body();
                Log.i(TAG, MessageFormat.format("Create campaign: {0}!", campaign));
                return campaign;
            } else {
                String errorMessage = MessageFormat.format(
                        "Error create to campaign! Return the code status: {0}.",
                        HttpStatus.valueOf(response.code()));
                validateErrorResponse(response, errorMessage);
            }
        } catch (IOException e) {
            Log.e(TAG_CLASSNAME, e.getLocalizedMessage(), e);
        }

        return campaign;
    }

}
