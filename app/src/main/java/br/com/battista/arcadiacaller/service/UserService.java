package br.com.battista.arcadiacaller.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import br.com.battista.arcadiacaller.model.User;

public interface UserService {

    @Nullable
    User findByUsername(@NonNull String token, @NonNull String username);

    @Nullable
    Boolean existsUsername(@NonNull String token, @NonNull String username);

    @Nullable
    User addFriends(@NonNull String token, @NonNull User user);

    @Nullable
    User create(@NonNull String token, @NonNull User user);
}
