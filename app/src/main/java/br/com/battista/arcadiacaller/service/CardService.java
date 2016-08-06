package br.com.battista.arcadiacaller.service;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.battista.arcadiacaller.model.Card;
import br.com.battista.arcadiacaller.model.enuns.GroupCardEnum;

public interface CardService {

    @NonNull
    List<Card> findAll(@NonNull String token);

    @NonNull
    List<Card> findByGroup(@NonNull String token, @NonNull GroupCardEnum groupCard);
}
