package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import br.com.battista.arcadiacaller.R;

import static br.com.battista.arcadiacaller.constants.HttpStatus.CREATED;

public enum CampaignStatusEnum {

    CREATED_CAMPAIGN(R.string.campaign_status_created),
    EDITED_SCENERY(R.string.campaign_status_playing),
    ADDED_SCENERY(R.string.campaign_status_playing),
    COMPLETED_CAMPAIGN(R.string.campaign_status_completed);

    private static final Map<String, CampaignStatusEnum> LOOK_UP = Maps.newHashMap();

    static {
        for (CampaignStatusEnum status :
                CampaignStatusEnum.values()) {
            LOOK_UP.put(status.name().toUpperCase(), status);
        }
    }

    private int descRes;

    CampaignStatusEnum(int descRes) {
        this.descRes = descRes;
    }

    public static CampaignStatusEnum get(String typeCard) {
        return LOOK_UP.get(MoreObjects.firstNonNull(typeCard, CREATED.name()).toUpperCase());
    }

    public int getDescRes() {
        return descRes;
    }
}
