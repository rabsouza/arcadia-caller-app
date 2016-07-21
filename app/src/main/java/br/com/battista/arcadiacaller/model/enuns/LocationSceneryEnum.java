package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public enum LocationSceneryEnum {
    NONE(R.string.location_scenery_none),
    INNER_CIRCLE(R.string.location_scenery_inner_circle),
    OUT_CIRCLE(R.string.location_scenery_out_circle),
    ULTIMATE(R.string.location_scenery_ultimate);

    private static final Map<String, LocationSceneryEnum> LOOK_UP = Maps.newHashMap();

    @Getter
    private int descRes;

    static {
        for (LocationSceneryEnum groupCard :
                LocationSceneryEnum.values()) {
            LOOK_UP.put(groupCard.name().toUpperCase(), groupCard);
        }
    }

    private LocationSceneryEnum(int descRes) {
        this.descRes = descRes;
    }

    public static LocationSceneryEnum get(String groupCard) {
        return LOOK_UP.get(MoreObjects.firstNonNull(groupCard, NONE.name()).toUpperCase());
    }

}
