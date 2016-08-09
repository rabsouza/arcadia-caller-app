package br.com.battista.arcadiacaller.model;

import com.google.common.base.MoreObjects;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

import br.com.battista.arcadiacaller.repository.contract.DatabaseContract.StatisticUserEntry;

@Table(name = StatisticUserEntry.TABLE_NAME)
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public Integer getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Integer campaigns) {
        this.campaigns = campaigns;
    }

    public Integer getCompleteds() {
        return completeds;
    }

    public void setCompleteds(Integer completeds) {
        this.completeds = completeds;
    }

    public Integer getSceneries() {
        return sceneries;
    }

    public void setSceneries(Integer sceneries) {
        this.sceneries = sceneries;
    }

    public Integer getGuilds() {
        return guilds;
    }

    public void setGuilds(Integer guilds) {
        this.guilds = guilds;
    }

    public Integer getCampaignWinners() {
        return campaignWinners;
    }

    public void setCampaignWinners(Integer campaignWinners) {
        this.campaignWinners = campaignWinners;
    }

    public Integer getCampaignDefeats() {
        return campaignDefeats;
    }

    public void setCampaignDefeats(Integer campaignDefeats) {
        this.campaignDefeats = campaignDefeats;
    }

    public Integer getCampaignMedalsWinners() {
        return campaignMedalsWinners;
    }

    public void setCampaignMedalsWinners(Integer campaignMedalsWinners) {
        this.campaignMedalsWinners = campaignMedalsWinners;
    }

    public Integer getCampaignMedalsLeastDeaths() {
        return campaignMedalsLeastDeaths;
    }

    public void setCampaignMedalsLeastDeaths(Integer campaignMedalsLeastDeaths) {
        this.campaignMedalsLeastDeaths = campaignMedalsLeastDeaths;
    }

    public Integer getCampaignMedalsMostCoins() {
        return campaignMedalsMostCoins;
    }

    public void setCampaignMedalsMostCoins(Integer campaignMedalsMostCoins) {
        this.campaignMedalsMostCoins = campaignMedalsMostCoins;
    }

    public Integer getCampaignMedalsWonRewards() {
        return campaignMedalsWonRewards;
    }

    public void setCampaignMedalsWonRewards(Integer campaignMedalsWonRewards) {
        this.campaignMedalsWonRewards = campaignMedalsWonRewards;
    }

    public Integer getCampaignMedalsWonTitles() {
        return campaignMedalsWonTitles;
    }

    public void setCampaignMedalsWonTitles(Integer campaignMedalsWonTitles) {
        this.campaignMedalsWonTitles = campaignMedalsWonTitles;
    }

    public Integer getSceneryWinners() {
        return sceneryWinners;
    }

    public void setSceneryWinners(Integer sceneryWinners) {
        this.sceneryWinners = sceneryWinners;
    }

    public Integer getSceneryDefeats() {
        return sceneryDefeats;
    }

    public void setSceneryDefeats(Integer sceneryDefeats) {
        this.sceneryDefeats = sceneryDefeats;
    }

    public Integer getSceneryLeastDeaths() {
        return sceneryLeastDeaths;
    }

    public void setSceneryLeastDeaths(Integer sceneryLeastDeaths) {
        this.sceneryLeastDeaths = sceneryLeastDeaths;
    }

    public Integer getSceneryMostCoins() {
        return sceneryMostCoins;
    }

    public void setSceneryMostCoins(Integer sceneryMostCoins) {
        this.sceneryMostCoins = sceneryMostCoins;
    }

    public Integer getSceneryWonRewards() {
        return sceneryWonRewards;
    }

    public void setSceneryWonRewards(Integer sceneryWonRewards) {
        this.sceneryWonRewards = sceneryWonRewards;
    }

    public Integer getSceneryWonTitles() {
        return sceneryWonTitles;
    }

    public void setSceneryWonTitles(Integer sceneryWonTitles) {
        this.sceneryWonTitles = sceneryWonTitles;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", username)
                .add("mail", mail)
                .add("urlAvatar", urlAvatar)
                .add("campaigns", campaigns)
                .add("completeds", completeds)
                .add("sceneries", sceneries)
                .add("guilds", guilds)
                .add("campaignWinners", campaignWinners)
                .add("campaignDefeats", campaignDefeats)
                .add("campaignMedalsWinners", campaignMedalsWinners)
                .add("campaignMedalsLeastDeaths", campaignMedalsLeastDeaths)
                .add("campaignMedalsMostCoins", campaignMedalsMostCoins)
                .add("campaignMedalsWonRewards", campaignMedalsWonRewards)
                .add("campaignMedalsWonTitles", campaignMedalsWonTitles)
                .add("sceneryWinners", sceneryWinners)
                .add("sceneryDefeats", sceneryDefeats)
                .add("sceneryLeastDeaths", sceneryLeastDeaths)
                .add("sceneryMostCoins", sceneryMostCoins)
                .add("sceneryWonRewards", sceneryWonRewards)
                .add("sceneryWonTitles", sceneryWonTitles)
                .toString();
    }

    public StatisticUser username(String username) {
        this.username = username;
        return this;
    }

    public StatisticUser mail(String mail) {
        this.mail = mail;
        return this;
    }

    public StatisticUser urlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
        return this;
    }

    public StatisticUser campaigns(Integer campaigns) {
        this.campaigns = campaigns;
        return this;
    }

    public StatisticUser completeds(Integer completeds) {
        this.completeds = completeds;
        return this;
    }

    public StatisticUser sceneries(Integer sceneries) {
        this.sceneries = sceneries;
        return this;
    }

    public StatisticUser guilds(Integer guilds) {
        this.guilds = guilds;
        return this;
    }

    public StatisticUser campaignWinners(Integer campaignWinners) {
        this.campaignWinners = campaignWinners;
        return this;
    }

    public StatisticUser campaignDefeats(Integer campaignDefeats) {
        this.campaignDefeats = campaignDefeats;
        return this;
    }

    public StatisticUser campaignMedalsWinners(Integer campaignMedalsWinners) {
        this.campaignMedalsWinners = campaignMedalsWinners;
        return this;
    }

    public StatisticUser campaignMedalsLeastDeaths(Integer campaignMedalsLeastDeaths) {
        this.campaignMedalsLeastDeaths = campaignMedalsLeastDeaths;
        return this;
    }

    public StatisticUser campaignMedalsMostCoins(Integer campaignMedalsMostCoins) {
        this.campaignMedalsMostCoins = campaignMedalsMostCoins;
        return this;
    }

    public StatisticUser campaignMedalsWonRewards(Integer campaignMedalsWonRewards) {
        this.campaignMedalsWonRewards = campaignMedalsWonRewards;
        return this;
    }

    public StatisticUser campaignMedalsWonTitles(Integer campaignMedalsWonTitles) {
        this.campaignMedalsWonTitles = campaignMedalsWonTitles;
        return this;
    }

    public StatisticUser sceneryWinners(Integer sceneryWinners) {
        this.sceneryWinners = sceneryWinners;
        return this;
    }

    public StatisticUser sceneryDefeats(Integer sceneryDefeats) {
        this.sceneryDefeats = sceneryDefeats;
        return this;
    }

    public StatisticUser sceneryLeastDeaths(Integer sceneryLeastDeaths) {
        this.sceneryLeastDeaths = sceneryLeastDeaths;
        return this;
    }

    public StatisticUser sceneryMostCoins(Integer sceneryMostCoins) {
        this.sceneryMostCoins = sceneryMostCoins;
        return this;
    }

    public StatisticUser sceneryWonRewards(Integer sceneryWonRewards) {
        this.sceneryWonRewards = sceneryWonRewards;
        return this;
    }

    public StatisticUser sceneryWonTitles(Integer sceneryWonTitles) {
        this.sceneryWonTitles = sceneryWonTitles;
        return this;
    }
}
