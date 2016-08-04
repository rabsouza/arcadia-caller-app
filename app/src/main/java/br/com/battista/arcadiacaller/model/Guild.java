package br.com.battista.arcadiacaller.model;

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

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.GuildEntry;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(includeFieldNames = true, callSuper = true)
@Table(name = GuildEntry.TABLE_NAME)
public class Guild extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer victories = 0;

    private Integer defeats = 0;

    private List<String> benefitTitles;

    private List<String> rewardCards;

    @Column(name = GuildEntry.COLUMN_NAME_NAME, notNull = true)
    private NameGuildEnum name;

    private User user;

    private HeroGuild hero01;

    private HeroGuild hero02;

    private HeroGuild hero03;

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
