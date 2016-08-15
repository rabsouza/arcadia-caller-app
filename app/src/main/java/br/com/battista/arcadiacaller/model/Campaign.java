package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.CampaignEntry;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
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
            GuildDto guildDto = new GuildDto().username(guild01).name(heroesGuild01.getName());
            guilds.add(guildDto);
        }
        if (!Strings.isNullOrEmpty(guild02) && heroesGuild02 != null) {
            GuildDto guildDto = new GuildDto().username(guild02).name(heroesGuild02.getName());
            guilds.add(guildDto);
        }
        if (!Strings.isNullOrEmpty(guild03) && heroesGuild03 != null) {
            GuildDto guildDto = new GuildDto().username(guild03).name(heroesGuild03.getName());
            guilds.add(guildDto);
        }
        if (!Strings.isNullOrEmpty(guild04) && heroesGuild04 != null) {
            GuildDto guildDto = new GuildDto().username(guild04).name(heroesGuild04.getName());
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
            SceneryDto sceneryDto = new SceneryDto().name(scenery1.getName()).completed(scenery1.getCompleted());
            sceneries.add(sceneryDto);
        }
        if (scenery2 != null) {
            SceneryDto sceneryDto = new SceneryDto().name(scenery2.getName()).completed(scenery2.getCompleted());
            sceneries.add(sceneryDto);
        }
        if (scenery3 != null) {
            SceneryDto sceneryDto = new SceneryDto().name(scenery3.getName()).completed(scenery3.getCompleted());
            sceneries.add(sceneryDto);
        }
        if (scenery4 != null) {
            SceneryDto sceneryDto = new SceneryDto().name(scenery4.getName()).completed(scenery4.getCompleted());
            sceneries.add(sceneryDto);
        }
        if (scenery5 != null) {
            SceneryDto sceneryDto = new SceneryDto().name(scenery5.getName()).completed(scenery5.getCompleted());
            sceneries.add(sceneryDto);
        }
        if (scenery6 != null) {
            SceneryDto sceneryDto = new SceneryDto().name(scenery6.getName()).completed(scenery6.getCompleted());
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

    @Nullable
    public Guild getGuildPosition(@NonNull Integer position) {
        List<Guild> guilds = Lists.newLinkedList();
        if (!Strings.isNullOrEmpty(guild01) && heroesGuild01 != null) {
            guilds.add(heroesGuild01);
        }
        if (!Strings.isNullOrEmpty(guild02) && heroesGuild02 != null) {
            guilds.add(heroesGuild02);
        }
        if (!Strings.isNullOrEmpty(guild03) && heroesGuild03 != null) {
            guilds.add(heroesGuild03);
        }
        if (!Strings.isNullOrEmpty(guild04) && heroesGuild04 != null) {
            guilds.add(heroesGuild04);
        }

        if (guilds.size() > position) {
            return guilds.get(position);
        } else {
            return null;
        }
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public String getGuild01() {
        return guild01;
    }

    public void setGuild01(String guild01) {
        this.guild01 = guild01;
    }

    public Guild getHeroesGuild01() {
        return heroesGuild01;
    }

    public void setHeroesGuild01(Guild heroesGuild01) {
        this.heroesGuild01 = heroesGuild01;
    }

    public String getGuild02() {
        return guild02;
    }

    public void setGuild02(String guild02) {
        this.guild02 = guild02;
    }

    public Guild getHeroesGuild02() {
        return heroesGuild02;
    }

    public void setHeroesGuild02(Guild heroesGuild02) {
        this.heroesGuild02 = heroesGuild02;
    }

    public String getGuild03() {
        return guild03;
    }

    public void setGuild03(String guild03) {
        this.guild03 = guild03;
    }

    public Guild getHeroesGuild03() {
        return heroesGuild03;
    }

    public void setHeroesGuild03(Guild heroesGuild03) {
        this.heroesGuild03 = heroesGuild03;
    }

    public String getGuild04() {
        return guild04;
    }

    public void setGuild04(String guild04) {
        this.guild04 = guild04;
    }

    public Guild getHeroesGuild04() {
        return heroesGuild04;
    }

    public void setHeroesGuild04(Guild heroesGuild04) {
        this.heroesGuild04 = heroesGuild04;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SceneryCampaign getScenery1() {
        return scenery1;
    }

    public void setScenery1(SceneryCampaign scenery1) {
        this.scenery1 = scenery1;
    }

    public SceneryCampaign getScenery2() {
        return scenery2;
    }

    public void setScenery2(SceneryCampaign scenery2) {
        this.scenery2 = scenery2;
    }

    public SceneryCampaign getScenery3() {
        return scenery3;
    }

    public void setScenery3(SceneryCampaign scenery3) {
        this.scenery3 = scenery3;
    }

    public SceneryCampaign getScenery4() {
        return scenery4;
    }

    public void setScenery4(SceneryCampaign scenery4) {
        this.scenery4 = scenery4;
    }

    public SceneryCampaign getScenery5() {
        return scenery5;
    }

    public void setScenery5(SceneryCampaign scenery5) {
        this.scenery5 = scenery5;
    }

    public SceneryCampaign getScenery6() {
        return scenery6;
    }

    public void setScenery6(SceneryCampaign scenery6) {
        this.scenery6 = scenery6;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<String> getWinners() {
        return winners;
    }

    public void setWinners(List<String> winners) {
        this.winners = winners;
    }

    public List<String> getLeastDeaths() {
        return leastDeaths;
    }

    public void setLeastDeaths(List<String> leastDeaths) {
        this.leastDeaths = leastDeaths;
    }

    public List<String> getMostCoins() {
        return mostCoins;
    }

    public void setMostCoins(List<String> mostCoins) {
        this.mostCoins = mostCoins;
    }

    public List<String> getWonReward() {
        return wonReward;
    }

    public void setWonReward(List<String> wonReward) {
        this.wonReward = wonReward;
    }

    public List<String> getWonTitle() {
        return wonTitle;
    }

    public void setWonTitle(List<String> wonTitle) {
        this.wonTitle = wonTitle;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campaign)) return false;
        if (!super.equals(o)) return false;
        Campaign campaign = (Campaign) o;
        return Objects.equal(getKey(), campaign.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getKey());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("alias", alias)
                .add("when", when)
                .add("guild01", guild01)
                .add("heroesGuild01", heroesGuild01)
                .add("guild02", guild02)
                .add("heroesGuild02", heroesGuild02)
                .add("guild03", guild03)
                .add("heroesGuild03", heroesGuild03)
                .add("guild04", guild04)
                .add("heroesGuild04", heroesGuild04)
                .add("key", key)
                .add("scenery1", scenery1)
                .add("scenery2", scenery2)
                .add("scenery3", scenery3)
                .add("scenery4", scenery4)
                .add("scenery5", scenery5)
                .add("scenery6", scenery6)
                .add("created", created)
                .add("winner", winner)
                .add("winners", winners)
                .add("leastDeaths", leastDeaths)
                .add("mostCoins", mostCoins)
                .add("wonReward", wonReward)
                .add("wonTitle", wonTitle)
                .add("active", active)
                .add("completed", completed)
                .add("deleted", deleted)
                .toString();
    }

    public Campaign alias(String alias) {
        this.alias = alias;
        return this;
    }

    public Campaign when(Date when) {
        this.when = when;
        return this;
    }

    public Campaign guild01(String guild01) {
        this.guild01 = guild01;
        return this;
    }

    public Campaign heroesGuild01(Guild heroesGuild01) {
        this.heroesGuild01 = heroesGuild01;
        return this;
    }

    public Campaign guild02(String guild02) {
        this.guild02 = guild02;
        return this;
    }

    public Campaign heroesGuild02(Guild heroesGuild02) {
        this.heroesGuild02 = heroesGuild02;
        return this;
    }

    public Campaign guild03(String guild03) {
        this.guild03 = guild03;
        return this;
    }

    public Campaign heroesGuild03(Guild heroesGuild03) {
        this.heroesGuild03 = heroesGuild03;
        return this;
    }

    public Campaign guild04(String guild04) {
        this.guild04 = guild04;
        return this;
    }

    public Campaign heroesGuild04(Guild heroesGuild04) {
        this.heroesGuild04 = heroesGuild04;
        return this;
    }

    public Campaign key(String key) {
        this.key = key;
        return this;
    }

    public Campaign scenery1(SceneryCampaign scenery1) {
        this.scenery1 = scenery1;
        return this;
    }

    public Campaign scenery2(SceneryCampaign scenery2) {
        this.scenery2 = scenery2;
        return this;
    }

    public Campaign scenery3(SceneryCampaign scenery3) {
        this.scenery3 = scenery3;
        return this;
    }

    public Campaign scenery4(SceneryCampaign scenery4) {
        this.scenery4 = scenery4;
        return this;
    }

    public Campaign scenery5(SceneryCampaign scenery5) {
        this.scenery5 = scenery5;
        return this;
    }

    public Campaign scenery6(SceneryCampaign scenery6) {
        this.scenery6 = scenery6;
        return this;
    }

    public Campaign created(String created) {
        this.created = created;
        return this;
    }

    public Campaign winner(String winner) {
        this.winner = winner;
        return this;
    }

    public Campaign winners(List<String> winners) {
        this.winners = winners;
        return this;
    }

    public Campaign leastDeaths(List<String> leastDeaths) {
        this.leastDeaths = leastDeaths;
        return this;
    }

    public Campaign mostCoins(List<String> mostCoins) {
        this.mostCoins = mostCoins;
        return this;
    }

    public Campaign wonReward(List<String> wonReward) {
        this.wonReward = wonReward;
        return this;
    }

    public Campaign wonTitle(List<String> wonTitle) {
        this.wonTitle = wonTitle;
        return this;
    }

    public Campaign active(Boolean active) {
        this.active = active;
        return this;
    }

    public Campaign completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    public Campaign deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
