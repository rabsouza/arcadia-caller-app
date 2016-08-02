package br.com.battista.arcadiacaller.service;

import android.support.annotation.NonNull;

import br.com.battista.arcadiacaller.model.StatisticUser;

public interface StatisticUserService {

    @NonNull
    StatisticUser findByUser(@NonNull String token, @NonNull String username);
}
