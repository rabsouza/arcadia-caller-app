package br.com.battista.arcadiacaller.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

import br.com.battista.arcadiacaller.repository.contract.DatabaseContract.StatisticUserEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.BaseEntry;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = StatisticUserEntry.TABLE_NAME, id = BaseEntry.COLUMN_NAME_PK)
public class StatisticUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = StatisticUserEntry.COLUMN_NAME_USERNAME, notNull = true)
    private String username;

    @Column(name = StatisticUserEntry.COLUMN_NAME_MAIL, notNull = true)
    private String mail;

    @Column(name = StatisticUserEntry.COLUMN_NAME_URL_AVATAR, notNull = true)
    private String urlAvatar;

    @Column(name = StatisticUserEntry.COLUMN_NAME_CAMPAIGNS, notNull = true)
    private Integer campaigns = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_COMPLETEDS, notNull = true)
    private Integer completeds = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_SCENERIES, notNull = true)
    private Integer sceneries = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_GUILDS, notNull = true)
    private Integer guilds = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_CAMPAIGN_WINNERS, notNull = true)
    private Integer campaignWinners = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_CAMPAIGN_DEFEATS, notNull = true)
    private Integer campaignDefeats = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_CAMPAIGN_MEDALS_WINNERS, notNull = true)
    private Integer campaignMedalsWinners = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_CAMPAIGN_MEDALS_LEAST_DEATHS, notNull = true)
    private Integer campaignMedalsLeastDeaths = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_CAMPAIGN_MEDALS_MOST_COINS, notNull = true)
    private Integer campaignMedalsMostCoins = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_CAMPAIGN_MEDALS_WON_REWARDS, notNull = true)
    private Integer campaignMedalsWonRewards = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_CAMPAIGN_MEDALS_WON_TITLES, notNull = true)
    private Integer campaignMedalsWonTitles = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_SCENERY_WINNERS, notNull = true)
    private Integer sceneryWinners = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_SCENERY_DEFEATS, notNull = true)
    private Integer sceneryDefeats = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_SCENERY_LEAST_DEATHS, notNull = true)
    private Integer sceneryLeastDeaths = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_SCENERY_MOST_COINS, notNull = true)
    private Integer sceneryMostCoins = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_SCENERY_WON_REWARDS, notNull = true)
    private Integer sceneryWonRewards = 0;

    @Column(name = StatisticUserEntry.COLUMN_NAME_SCENERY_WON_TITLES, notNull = true)
    private Integer sceneryWonTitles = 0;

    public void initializeStatistic() {
        campaigns = 0;
        completeds = 0;
        sceneries = 0;
        guilds = 0;
        campaignWinners = 0;
        campaignDefeats = 0;
        campaignMedalsWinners = 0;
        campaignMedalsLeastDeaths = 0;
        campaignMedalsMostCoins = 0;
        campaignMedalsWonRewards = 0;
        campaignMedalsWonTitles = 0;
        sceneryWinners = 0;
        sceneryDefeats = 0;
        sceneryLeastDeaths = 0;
        sceneryMostCoins = 0;
        sceneryWonRewards = 0;
        sceneryWonTitles = 0;
    }

}
