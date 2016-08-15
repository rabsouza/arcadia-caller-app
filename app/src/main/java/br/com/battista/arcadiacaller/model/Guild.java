package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.GuildEntry;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.List;

import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;

@Table(name = GuildEntry.TABLE_NAME)
public class Guild extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = GuildEntry.COLUMN_NAME_VICTORIES, notNull = true)
    private Integer victories = 0;

    @Column(name = GuildEntry.COLUMN_NAME_DEFEATS, notNull = true)
    private Integer defeats = 0;

    @Column(name = GuildEntry.COLUMN_NAME_BENEFIT_TITLES)
    private List<String> benefitTitles;

    @Column(name = GuildEntry.COLUMN_NAME_REWARD_CARDS)
    private List<String> rewardCards;

    @Column(name = GuildEntry.COLUMN_NAME_NAME, notNull = true)
    private NameGuildEnum name;

    @Column(name = GuildEntry.COLUMN_NAME_USER, notNull = true)
    private User user;

    @Column(name = GuildEntry.COLUMN_NAME_HERO_01, notNull = true)
    private HeroGuild hero01;

    @Column(name = GuildEntry.COLUMN_NAME_HERO_02, notNull = true)
    private HeroGuild hero02;

    @Column(name = GuildEntry.COLUMN_NAME_HERO_03, notNull = true)
    private HeroGuild hero03;

    @Column(name = GuildEntry.COLUMN_NAME_SAVED_MONEY, notNull = true)
    private Boolean savedMoney = Boolean.FALSE;

    public void incVictories() {
        if (victories == null) {
            victories = 0;
        }
        victories++;
    }

    public void incDefeats() {
        if (defeats == null) {
            defeats = 0;
        }
        defeats++;
    }

    public void addBenefitTitles(String title) {
        if (benefitTitles == null) {
            benefitTitles = Lists.newArrayList();
        }
        benefitTitles.add(title);
    }

    public void addRewardCards(String rewardCard) {
        if (rewardCards == null) {
            rewardCards = Lists.newArrayList();
        }
        rewardCards.add(rewardCard);
    }

    public Integer getVictories() {
        return victories;
    }

    public void setVictories(Integer victories) {
        this.victories = victories;
    }

    public Integer getDefeats() {
        return defeats;
    }

    public void setDefeats(Integer defeats) {
        this.defeats = defeats;
    }

    public List<String> getBenefitTitles() {
        return benefitTitles;
    }

    public void setBenefitTitles(List<String> benefitTitles) {
        this.benefitTitles = benefitTitles;
    }

    public List<String> getRewardCards() {
        return rewardCards;
    }

    public void setRewardCards(List<String> rewardCards) {
        this.rewardCards = rewardCards;
    }

    public NameGuildEnum getName() {
        return name;
    }

    public void setName(NameGuildEnum name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HeroGuild getHero01() {
        return hero01;
    }

    public void setHero01(HeroGuild hero01) {
        this.hero01 = hero01;
    }

    public HeroGuild getHero02() {
        return hero02;
    }

    public void setHero02(HeroGuild hero02) {
        this.hero02 = hero02;
    }

    public HeroGuild getHero03() {
        return hero03;
    }

    public void setHero03(HeroGuild hero03) {
        this.hero03 = hero03;
    }

    public Boolean getSavedMoney() {
        if(savedMoney == null){
            savedMoney = Boolean.FALSE;
        }
        return savedMoney;
    }

    public void setSavedMoney(Boolean savedMoney) {
        this.savedMoney = savedMoney;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("victories", victories)
                .add("defeats", defeats)
                .add("benefitTitles", benefitTitles)
                .add("rewardCards", rewardCards)
                .add("name", name)
                .add("user", user)
                .add("hero01", hero01)
                .add("hero02", hero02)
                .add("hero03", hero03)
                .add("savedMoney", savedMoney)
                .toString();
    }


    public Guild victories(Integer victories) {
        this.victories = victories;
        return this;
    }

    public Guild defeats(Integer defeats) {
        this.defeats = defeats;
        return this;
    }

    public Guild benefitTitles(List<String> benefitTitles) {
        this.benefitTitles = benefitTitles;
        return this;
    }

    public Guild rewardCards(List<String> rewardCards) {
        this.rewardCards = rewardCards;
        return this;
    }

    public Guild name(NameGuildEnum name) {
        this.name = name;
        return this;
    }

    public Guild user(User user) {
        this.user = user;
        return this;
    }

    public Guild hero01(HeroGuild hero01) {
        this.hero01 = hero01;
        return this;
    }

    public Guild hero02(HeroGuild hero02) {
        this.hero02 = hero02;
        return this;
    }

    public Guild hero03(HeroGuild hero03) {
        this.hero03 = hero03;
        return this;
    }

    public Guild savedMoney(Boolean savedMoney) {
        this.savedMoney = savedMoney;
        return this;
    }
}
