package br.com.battista.arcadiacaller.model.enuns;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import br.com.battista.arcadiacaller.R;
import lombok.Getter;

public enum GroupCardEnum {

    NONE(R.string.group_card_none, R.color.colorCardGroupNone),
    STARTING_EQUIPMENT(R.string.group_card_starting_equipment, R.color.colorCardGroupSartingEquipment),
    DEATH_CURSE(R.string.group_card_death_curse, R.color.colorCardGroupDeathCurse),
    REWARD_CARDS(R.string.group_card_reward_cards, R.color.colorCardGroupRewardCards),
    LEVEL_1(R.string.group_card_level_1, R.color.colorCardGroupLevel1),
    LEVEL_2(R.string.group_card_level_2, R.color.colorCardGroupLevel2),
    LEVEL_3(R.string.group_card_level_3, R.color.colorCardGroupLevel3),
    LEVEL_4(R.string.group_card_level_4, R.color.colorCardGroupLevel4),
    LEVEL_5(R.string.group_card_level_5, R.color.colorCardGroupLevel5);

    private static final Map<String, GroupCardEnum> LOOK_UP = Maps.newHashMap();

    static {
        for (GroupCardEnum groupCard :
                GroupCardEnum.values()) {
            LOOK_UP.put(groupCard.name().toUpperCase(), groupCard);
        }
    }

    @Getter
    private int descRes;
    @Getter
    private int colorRes;

    GroupCardEnum(int descRes, int colorRes) {
        this.descRes = descRes;
        this.colorRes = colorRes;
    }

    public static GroupCardEnum get(String groupCard) {
        return LOOK_UP.get(MoreObjects.firstNonNull(groupCard, NONE.name()).toUpperCase());
    }

}
