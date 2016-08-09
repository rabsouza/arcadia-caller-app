package br.com.battista.arcadiacaller.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.List;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.SceneryCampaignEntry;

@Table(name = SceneryCampaignEntry.TABLE_NAME)
public class SceneryCampaign extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = SceneryCampaignEntry.COLUMN_NAME_NAME, notNull = true)
    private String name;

    @Column(name = SceneryCampaignEntry.COLUMN_NAME_SCENERY, notNull = true)
    private Scenery scenery;

    @Column(name = SceneryCampaignEntry.COLUMN_NAME_WINNER)
    private String winner;

    @Column(name = SceneryCampaignEntry.COLUMN_NAME_LEAST_DEATHS)
    private List<String> leastDeaths;

    @Column(name = SceneryCampaignEntry.COLUMN_NAME_MOST_COINS)
    private List<String> mostCoins;

    @Column(name = SceneryCampaignEntry.COLUMN_NAME_WON_REWARD)
    private List<String> wonReward;

    @Column(name = SceneryCampaignEntry.COLUMN_NAME_WON_TITLE)
    private List<String> wonTitle;

    @Column(name = SceneryCampaignEntry.COLUMN_NAME_ACTIVE, notNull = true)
    private Boolean active = Boolean.TRUE;

    @Column(name = SceneryCampaignEntry.COLUMN_NAME_COMPLETED, notNull = true)
    private Boolean completed = Boolean.FALSE;

    @Column(name = SceneryCampaignEntry.COLUMN_NAME_DELETED, notNull = true)
    private Boolean deleted = Boolean.FALSE;

    public void addLeastDeaths(String guild) {
        if (leastDeaths == null) {
            leastDeaths = Lists.newArrayList();
        }
        leastDeaths.add(guild);
    }

    public void addMostCoins(String guild) {
        if (mostCoins == null) {
            mostCoins = Lists.newArrayList();
        }
        mostCoins.add(guild);
    }

    public void addWonReward(String guild) {
        if (wonReward == null) {
            wonReward = Lists.newArrayList();
        }
        wonReward.add(guild);
    }

    public void addWonTitle(String guild) {
        if (wonTitle == null) {
            wonTitle = Lists.newArrayList();
        }
        wonTitle.add(guild);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scenery getScenery() {
        return scenery;
    }

    public void setScenery(Scenery scenery) {
        this.scenery = scenery;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
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
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("scenery", scenery)
                .add("winner", winner)
                .add("leastDeaths", leastDeaths)
                .add("mostCoins", mostCoins)
                .add("wonReward", wonReward)
                .add("wonTitle", wonTitle)
                .add("active", active)
                .add("completed", completed)
                .add("deleted", deleted)
                .toString();
    }

    public SceneryCampaign name(String name) {
        this.name = name;
        return this;
    }

    public SceneryCampaign scenery(Scenery scenery) {
        this.scenery = scenery;
        return this;
    }

    public SceneryCampaign winner(String winner) {
        this.winner = winner;
        return this;
    }

    public SceneryCampaign leastDeaths(List<String> leastDeaths) {
        this.leastDeaths = leastDeaths;
        return this;
    }

    public SceneryCampaign mostCoins(List<String> mostCoins) {
        this.mostCoins = mostCoins;
        return this;
    }

    public SceneryCampaign wonReward(List<String> wonReward) {
        this.wonReward = wonReward;
        return this;
    }

    public SceneryCampaign wonTitle(List<String> wonTitle) {
        this.wonTitle = wonTitle;
        return this;
    }

    public SceneryCampaign active(Boolean active) {
        this.active = active;
        return this;
    }

    public SceneryCampaign completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    public SceneryCampaign deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
