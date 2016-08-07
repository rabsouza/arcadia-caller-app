package br.com.battista.arcadiacaller.service.database;

import android.support.annotation.NonNull;
import android.util.Log;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.model.enuns.GroupHeroEnum;
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

    @NonNull
    @Override
    public List<Hero> findByGroup(@NonNull String token, @NonNull GroupHeroEnum groupHero) {
        Log.i(TAG, MessageFormat.format("Find heroes by group: {0} in database!", groupHero.name()));

        return heroRepository.findByGroup(groupHero);
    }
}
