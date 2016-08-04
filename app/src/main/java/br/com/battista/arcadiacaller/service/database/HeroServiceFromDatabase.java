package br.com.battista.arcadiacaller.service.database;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.repository.HeroRepository;
import br.com.battista.arcadiacaller.service.HeroService;

public class HeroServiceFromDatabase implements HeroService {

    private static final String TAG = HeroServiceFromDatabase.class.getSimpleName();

    private final HeroRepository heroRepository;

    public HeroServiceFromDatabase() {
        heroRepository = new HeroRepository();
    }

    @NonNull
    @Override
    public List<Hero> findAll(@NonNull String token) {
        Log.i(TAG, "Find all heroes in database!");

        return heroRepository.findAll();
    }
}