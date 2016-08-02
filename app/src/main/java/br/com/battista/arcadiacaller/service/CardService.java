package br.com.battista.arcadiacaller.service;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.battista.arcadiacaller.model.Card;

public interface CardService {

    @NonNull
    List<Card> findAll(@NonNull String token);
}
