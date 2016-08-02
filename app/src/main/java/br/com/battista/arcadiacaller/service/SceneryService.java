package br.com.battista.arcadiacaller.service;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;

public interface SceneryService {

    @NonNull
    List<Scenery> findAll(@NonNull String token);

    @NonNull
    List<Scenery> findByLocation(@NonNull String token, LocationSceneryEnum location);
}
