package br.com.battista.arcadiacaller.service;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.battista.arcadiacaller.model.Hero;

public interface HeroService {

    @NonNull
    List<Hero> findAll(@NonNull String token);
}
