package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import static br.com.battista.arcadiacaller.constants.HttpStatus.CREATED;

public enum CampaignStatusEnum {

    CREATED_CAMPAIGN,
    EDITED_SCENERY,
    ADDED_SCENERY,
    COMPLETED_CAMPAIGN;

    private static final Map<String, CampaignStatusEnum> LOOK_UP = Maps.newHashMap();

    static {
        for (CampaignStatusEnum status :
                CampaignStatusEnum.values()) {
            LOOK_UP.put(status.name().toUpperCase(), status);
        }
    }

    public static CampaignStatusEnum get(String typeCard) {
        return LOOK_UP.get(MoreObjects.firstNonNull(typeCard, CREATED.name()).toUpperCase());
    }

}
