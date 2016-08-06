package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.SceneryCampaignEntry;

import com.google.common.collect.Lists;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.List;

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
@EqualsAndHashCode(of = {"name"}, callSuper = false)
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

}
