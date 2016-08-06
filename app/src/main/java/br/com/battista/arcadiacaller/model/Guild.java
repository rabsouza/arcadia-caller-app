package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.GuildEntry;

import com.google.common.collect.Lists;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.List;

import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
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
@EqualsAndHashCode(callSuper = false)
@ToString( callSuper = true)
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

}
