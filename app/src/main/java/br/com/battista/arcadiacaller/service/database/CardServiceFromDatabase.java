package br.com.battista.arcadiacaller.service.database;

import android.support.annotation.NonNull;
import android.util.Log;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.Card;
import br.com.battista.arcadiacaller.model.enuns.GroupCardEnum;
import br.com.battista.arcadiacaller.repository.CardRepository;
import br.com.battista.arcadiacaller.service.CardService;

public class CardServiceFromDatabase implements CardService {

    private static final String TAG = CardServiceFromDatabase.class.getSimpleName();

    private final CardRepository cardRepository;

    public CardServiceFromDatabase() {
        cardRepository = new CardRepository();
    }

    @NonNull
    @Override
    public List<Card> findAll(@NonNull String token) {
        Log.i(TAG, "Find all cards in database!");

        return cardRepository.findAll();
    }

    @NonNull
    @Override
    public List<Card> findByGroup(@NonNull String token, @NonNull GroupCardEnum groupCard) {
        Log.i(TAG, MessageFormat.format("Find cards by group: {0} in database!", groupCard.name()));

        return cardRepository.findByGroup(groupCard);
    }
}
