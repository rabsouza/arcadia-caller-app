package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.CampaignEntry;

import android.support.annotation.NonNull;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.battista.arcadiacaller.model.dto.GuildDto;
import br.com.battista.arcadiacaller.model.dto.SceneryDto;
import br.com.battista.arcadiacaller.model.enuns.CampaignStatusEnum;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Table(name = CampaignEntry.TABLE_NAME)
public class Campaign extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = CampaignEntry.COLUMN_NAME_ALIAS, notNull = true)
    private String alias;

    @Column(name = CampaignEntry.COLUMN_NAME_WHEN, notNull = true)
    private Date when;

    @Column(name = CampaignEntry.COLUMN_NAME_GUILD_01)
    private String guild01;

    @Column(name = CampaignEntry.COLUMN_NAME_HEROES_GUILD_01)
    private Guild heroesGuild01;

    @Column(name = CampaignEntry.COLUMN_NAME_GUILD_02)
    private String guild02;

    @Column(name = CampaignEntry.COLUMN_NAME_HEROES_GUILD_02)
    private Guild heroesGuild02;

    @Column(name = CampaignEntry.COLUMN_NAME_GUILD_03)
    private String guild03;

    @Column(name = CampaignEntry.COLUMN_NAME_HEROES_GUILD_03)
    private Guild heroesGuild03;

    @Column(name = CampaignEntry.COLUMN_NAME_GUILD_04)
    private String guild04;

    @Column(name = CampaignEntry.COLUMN_NAME_HEROES_GUILD_04)
    private Guild heroesGuild04;

    @Column(name = CampaignEntry.COLUMN_NAME_KEY, notNull = true, unique = true)
    private String key;

    @Column(name = CampaignEntry.COLUMN_NAME_SCENERY_01)
    private SceneryCampaign scenery1;

    @Column(name = CampaignEntry.COLUMN_NAME_SCENERY_02)
    private SceneryCampaign scenery2;

    @Column(name = CampaignEntry.COLUMN_NAME_SCENERY_03)
    private SceneryCampaign scenery3;

    @Column(name = CampaignEntry.COLUMN_NAME_SCENERY_04)
    private SceneryCampaign scenery4;

    @Column(name = CampaignEntry.COLUMN_NAME_SCENERY_05)
    private SceneryCampaign scenery5;

    @Column(name = CampaignEntry.COLUMN_NAME_SCENERY_06)
    private SceneryCampaign scenery6;

    @Column(name = CampaignEntry.COLUMN_NAME_CREATED)
    private String created;

    @Column(name = CampaignEntry.COLUMN_NAME_WINNER)
    private String winner;

    @Column(name = CampaignEntry.COLUMN_NAME_WINNERS)
    private List<String> winners;

    @Column(name = CampaignEntry.COLUMN_NAME_LEAST_DEATHS)
    private List<String> leastDeaths;

    @Column(name = CampaignEntry.COLUMN_NAME_MOST_COINS)
    private List<String> mostCoins;

    @Column(name = CampaignEntry.COLUMN_NAME_WON_REWARD)
    private List<String> wonReward;

    @Column(name = CampaignEntry.COLUMN_NAME_WON_TITLE)
    private List<String> wonTitle;

    @Column(name = CampaignEntry.COLUMN_NAME_ACTIVE, notNull = true)
    private Boolean active = Boolean.TRUE;

    @Column(name = CampaignEntry.COLUMN_NAME_COMPLETED, notNull = true)
    private Boolean completed = Boolean.FALSE;

    @Column(name = CampaignEntry.COLUMN_NAME_DELETED, notNull = true)
    private Boolean deleted = Boolean.FALSE;


    public List<GuildDto> generateGuildsDto() {
        List<GuildDto> guilds = Lists.newArrayList();

        if (!Strings.isNullOrEmpty(guild01) && heroesGuild01 != null) {
            GuildDto guildDto = GuildDto.builder().username(guild01).name(heroesGuild01.getName()).build();
            guilds.add(guildDto);
        }

        if (!Strings.isNullOrEmpty(guild02) && heroesGuild02 != null) {
            GuildDto guildDto = GuildDto.builder().username(guild02).name(heroesGuild02.getName()).build();
            guilds.add(guildDto);
        }

        if (!Strings.isNullOrEmpty(guild03) && heroesGuild03 != null) {
            GuildDto guildDto = GuildDto.builder().username(guild03).name(heroesGuild03.getName()).build();
            guilds.add(guildDto);
        }

        if (!Strings.isNullOrEmpty(guild04) && heroesGuild04 != null) {
            GuildDto guildDto = GuildDto.builder().username(guild04).name(heroesGuild04.getName()).build();
            guilds.add(guildDto);
        }
        return guilds;
    }

    public List<String> getAllActiveGuilds() {
        ArrayList<String> guilds = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(getGuild01())) {
            guilds.add(getGuild01());
        }
        if (!Strings.isNullOrEmpty(getGuild02())) {
            guilds.add(getGuild02());
        }
        if (!Strings.isNullOrEmpty(getGuild03())) {
            guilds.add(getGuild03());
        }
        if (!Strings.isNullOrEmpty(getGuild04())) {
            guilds.add(getGuild04());
        }
        return guilds;
    }

    public List<SceneryDto> generateSceneriesDto() {
        List<SceneryDto> sceneries = Lists.newArrayList();

        if (scenery1 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery1.getName()).completed(scenery1.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        if (scenery2 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery2.getName()).completed(scenery2.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        if (scenery3 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery3.getName()).completed(scenery3.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        if (scenery4 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery4.getName()).completed(scenery4.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        if (scenery5 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery5.getName()).completed(scenery5.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        if (scenery6 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery6.getName()).completed(scenery6.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        return sceneries;
    }

    @NonNull
    public LocationSceneryEnum getLocationCurrent() {
        LocationSceneryEnum locationScenery = LocationSceneryEnum.NONE;
        if (getScenery1() == null || getScenery2() == null || getScenery3() == null) {
            locationScenery = LocationSceneryEnum.OUT_CIRCLE;
        } else if (getScenery4() == null || getScenery5() == null) {
            locationScenery = LocationSceneryEnum.INNER_CIRCLE;
        } else if (getScenery6() == null || getScenery6().getCompleted()) {
            locationScenery = LocationSceneryEnum.ULTIMATE;
        }
        return locationScenery;
    }

    @NonNull
    public SceneryCampaign getSceneryCurrent() {
        SceneryCampaign scenery;
        if (scenery6 != null) {
            scenery = scenery6;
        } else if (scenery5 != null) {
            scenery = scenery5;
        } else if (scenery4 != null) {
            scenery = scenery4;
        } else if (scenery3 != null) {
            scenery = scenery3;
        } else if (scenery2 != null) {
            scenery = scenery2;
        } else if (scenery1 != null) {
            scenery = scenery1;
        } else {
            scenery = new SceneryCampaign();
        }
        return scenery;
    }

    @NonNull
    public SceneryCampaign getSceneryPosition(@NonNull Integer position) {
        SceneryCampaign scenery;
        if (scenery6 != null && position == 6) {
            scenery = scenery6;
        } else if (scenery5 != null && position == 5) {
            scenery = scenery5;
        } else if (scenery4 != null && position == 4) {
            scenery = scenery4;
        } else if (scenery3 != null && position == 3) {
            scenery = scenery3;
        } else if (scenery2 != null && position == 2) {
            scenery = scenery2;
        } else if (scenery1 != null && position == 1) {
            scenery = scenery1;
        } else {
            scenery = new SceneryCampaign();
        }
        return scenery;
    }

    @NonNull
    public SceneryCampaign getNextScenery() {
        SceneryCampaign scenery;
        if (scenery6 != null && !scenery6.getCompleted()) {
            scenery = scenery6;
        } else if (scenery5 != null && !scenery5.getCompleted()) {
            scenery = scenery5;
        } else if (scenery4 != null && !scenery4.getCompleted()) {
            scenery = scenery4;
        } else if (scenery3 != null && !scenery3.getCompleted()) {
            scenery = scenery3;
        } else if (scenery2 != null && !scenery2.getCompleted()) {
            scenery = scenery2;
        } else if (scenery1 != null && !scenery1.getCompleted()) {
            scenery = scenery1;
        } else if (scenery6 != null && scenery6.getCompleted()) {
            scenery = scenery6;
        } else {
            scenery = new SceneryCampaign();
        }
        return scenery;
    }

    public void addNextScenery(@NonNull SceneryCampaign scenery) {
        if (scenery1 == null || !scenery1.getCompleted()) {
            scenery1 = scenery;
        } else if (scenery2 == null || !scenery2.getCompleted()) {
            scenery2 = scenery;
        } else if (scenery3 == null || !scenery3.getCompleted()) {
            scenery3 = scenery;
        } else if (scenery4 == null || !scenery4.getCompleted()) {
            scenery4 = scenery;
        } else if (scenery5 == null || !scenery5.getCompleted()) {
            scenery5 = scenery;
        } else if (scenery6 == null || !scenery6.getCompleted()) {
            scenery6 = scenery;
        }
    }

    @NonNull
    public Boolean existsHeroes() {
        if (heroesGuild01 == null && heroesGuild02 == null && heroesGuild03 == null && heroesGuild04 == null) {
            return false;
        }

        return ((heroesGuild01 == null) || (heroesGuild01.getHero01() != null && heroesGuild01.getHero02() != null && heroesGuild01.getHero03() != null))
                && ((heroesGuild02 == null) || (heroesGuild02.getHero01() != null && heroesGuild02.getHero02() != null && heroesGuild02.getHero03() != null))
                && ((heroesGuild03 == null) || (heroesGuild03.getHero01() != null && heroesGuild03.getHero02() != null && heroesGuild03.getHero03() != null))
                && ((heroesGuild04 == null) || (heroesGuild04.getHero01() != null && heroesGuild04.getHero02() != null && heroesGuild04.getHero03() != null));
    }

    @NonNull
    public CampaignStatusEnum getStatusCurrent() {
        CampaignStatusEnum status = CampaignStatusEnum.CREATED_CAMPAIGN;
        if (scenery1 == null || !existsHeroes() || !scenery1.getCompleted()) {
            status = CampaignStatusEnum.CREATED_CAMPAIGN;
        } else if (((scenery1 != null && scenery1.getCompleted()) && scenery2 == null)
                || ((scenery2 != null && scenery2.getCompleted()) && scenery3 == null)
                || ((scenery3 != null && scenery3.getCompleted()) && scenery4 == null)
                || ((scenery4 != null && scenery4.getCompleted()) && scenery5 == null)
                || ((scenery5 != null && scenery5.getCompleted()) && scenery6 == null)) {
            status = CampaignStatusEnum.ADDED_SCENERY;
        } else if (((scenery1 != null && !scenery1.getCompleted()) && scenery2 == null)
                || ((scenery2 != null && !scenery2.getCompleted()) && scenery3 == null)
                || ((scenery3 != null && !scenery3.getCompleted()) && scenery4 == null)
                || ((scenery4 != null && !scenery4.getCompleted()) && scenery5 == null)
                || ((scenery5 != null && !scenery5.getCompleted()) && scenery6 == null)
                || (scenery6 != null && !scenery6.getCompleted())) {
            status = CampaignStatusEnum.EDITED_SCENERY;
        } else if (scenery6 != null && scenery6.getCompleted()) {
            status = CampaignStatusEnum.COMPLETED_CAMPAIGN;
        }
        return status;
    }

}
