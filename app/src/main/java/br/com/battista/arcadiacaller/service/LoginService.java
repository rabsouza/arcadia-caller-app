package br.com.battista.arcadiacaller.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import br.com.battista.arcadiacaller.model.User;

public interface LoginService {

    @Nullable
    String login(@NonNull String username);

    @Nullable
    User create(@NonNull User user);
}
