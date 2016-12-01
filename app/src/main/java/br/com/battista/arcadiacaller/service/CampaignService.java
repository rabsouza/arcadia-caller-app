package br.com.battista.arcadiacaller.service;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.battista.arcadiacaller.model.Campaign;

public interface CampaignService {

    @NonNull
    List<Campaign> findByUser(@NonNull String token, @NonNull String username);

    @NonNull
    Campaign findByKey(@NonNull String token, @NonNull String key);

    @NonNull
    Campaign create(@NonNull String token, @NonNull Campaign campaign);

    @NonNull
    Campaign update(@NonNull String token, @NonNull Campaign campaign);

    @NonNull
    Boolean delete(@NonNull String token, @NonNull Campaign campaign);
}
