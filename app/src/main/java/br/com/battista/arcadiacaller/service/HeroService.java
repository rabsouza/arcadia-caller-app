package br.com.battista.arcadiacaller.service;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.model.enuns.GroupHeroEnum;

public interface HeroService {

    @NonNull
    List<Hero> findAll(@NonNull String token);

    @NonNull
    List<Hero> findByGroup(@NonNull String token, @NonNull GroupHeroEnum groupHero);
}
