package br.com.battista.arcadiacaller.service.database;

import android.support.annotation.NonNull;
import android.util.Log;

import java.text.MessageFormat;
import java.util.List;

import br.com.battista.arcadiacaller.model.Scenery;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;
import br.com.battista.arcadiacaller.repository.SceneryRepository;
import br.com.battista.arcadiacaller.service.SceneryService;

public class SceneryServiceFromDatabase implements SceneryService {

    private static final String TAG = SceneryServiceFromDatabase.class.getSimpleName();

    private final SceneryRepository sceneryRepository;

    public SceneryServiceFromDatabase() {
        sceneryRepository = new SceneryRepository();
    }


    @NonNull
    @Override
    public List<Scenery> findAll(@NonNull String token) {
        Log.i(TAG, "Find all sceneries in database!");
        return sceneryRepository.findAll();
    }

    @NonNull
    @Override
    public List<Scenery> findByLocation(@NonNull String token, LocationSceneryEnum location) {
        Log.i(TAG, MessageFormat.format("Find all sceneries by location: {0} in database!", location));
        return sceneryRepository.findByLocation(location);
    }
}
