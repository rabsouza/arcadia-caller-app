package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import br.com.battista.arcadiacaller.R;

public enum LocationSceneryEnum {
    NONE(R.string.location_scenery_none, R.color.colorLocationSceneryNone),
    INNER_CIRCLE(R.string.location_scenery_inner_circle, R.color.colorLocationSceneryInnerCircle),
    OUT_CIRCLE(R.string.location_scenery_out_circle, R.color.colorLocationSceneryOutCircle),
    ULTIMATE(R.string.location_scenery_ultimate, R.color.colorLocationSceneryUltimate);

    private static final Map<String, LocationSceneryEnum> LOOK_UP = Maps.newHashMap();

    static {
        for (LocationSceneryEnum groupCard :
                LocationSceneryEnum.values()) {
            LOOK_UP.put(groupCard.name().toUpperCase(), groupCard);
        }
    }

    private int descRes;
    private int colorRes;

    LocationSceneryEnum(int descRes, int colorRes) {
        this.descRes = descRes;
        this.colorRes = colorRes;
    }

    public static LocationSceneryEnum get(String groupCard) {
        return LOOK_UP.get(MoreObjects.firstNonNull(groupCard, NONE.name()).toUpperCase());
    }

    public int getDescRes() {
        return descRes;
    }

    public int getColorRes() {
        return colorRes;
    }
}
