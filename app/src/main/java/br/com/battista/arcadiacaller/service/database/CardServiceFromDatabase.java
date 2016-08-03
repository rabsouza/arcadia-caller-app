package br.com.battista.arcadiacaller.service.database;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import br.com.battista.arcadiacaller.model.Card;
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
}
