package br.com.battista.arcadiacaller.repository;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.util.Log;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.Card;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.CardEntry;

public class CardRepository implements BaseRepository<Card> {

    public static final String TAG = CardRepository.class.getSimpleName();

    @Override
    public void save(Card entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Save to card with id: {0}.", entity.getPk()));
            ActiveAndroid.beginTransaction();
            try {
                entity.save();
                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
        } else {
            Log.w(TAG, "Entity can not be null!");
        }
    }

    @Override
    public void saveAll(List<Card> entities) {
        if (entities != null) {
            Log.i(TAG, MessageFormat.format("Save {0} cards.", entities.size()));
            ActiveAndroid.beginTransaction();
            try {
                for (Card entity : entities) {
                    if (entity != null) {
                        Log.i(TAG, MessageFormat.format("Save to card with id: {0}.", entity.getPk()));
                        entity.synchronize();
                        entity.save();
                    }
                }
                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
        } else {
            Log.w(TAG, "Entities can not be null!");
        }
    }

    @Override
    public Card findById(Long id) {
        Log.i(TAG, MessageFormat.format("Find the card by id: {0}.", id));
        return new Select()
                .from(Card.class)
                .where("id = ?", id)
                .executeSingle();
    }

    @Override
    public void update(Card entity) {
        if (entity != null) {
            Log.i(TAG, MessageFormat.format("Update the card with id: {0}.", entity.getPk()));
            ActiveAndroid.beginTransaction();
            try {
                entity.save();
                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
        } else {
            Log.i(TAG, "Entity can not be null!");
        }
    }

    @Override
    public void deleteById(Long id) {
        Log.i(TAG, MessageFormat.format("Delete the card with id: {0}.", id));
        ActiveAndroid.beginTransaction();
        try {
            new Delete()
                    .from(Card.class)
                    .where("id = ?", id)
                    .execute();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

    }

    @Override
    public List<Card> findAll() {
        Log.i(TAG, "Find all cards.");
        return new Select()
                .from(Card.class)
                .orderBy(MessageFormat.format("{0} ASC, {1} ASC", CardEntry.COLUMN_NAME_GROUP, CardEntry.COLUMN_NAME_NAME))
                .execute();
    }

    @Override
    public void deleteAll() {
        Log.i(TAG, "Delete all cards.");
        ActiveAndroid.beginTransaction();
        try {
            new Delete()
                    .from(Card.class)
                    .execute();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

    }
}
