package br.com.battista.arcadiacaller.model.enuns;

import java.io.Serializable;

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
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class StatisticUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String mail;

    private String urlAvatar;

    private Integer campaigns = 0;

    private Integer completeds = 0;

    private Integer sceneries = 0;

    private Integer guilds = 0;

    private Integer campaignWinners = 0;

    private Integer campaignDefeats = 0;

    private Integer campaignLeastDeaths = 0;

    private Integer campaignMostCoins = 0;

    private Integer campaignWonRewards = 0;

    private Integer campaignWonTitles = 0;

    private Integer sceneryWinners = 0;

    private Integer sceneryDefeats = 0;

    private Integer sceneryLeastDeaths = 0;

    private Integer sceneryMostCoins = 0;

    private Integer sceneryWonRewards = 0;

    private Integer sceneryWonTitles = 0;

    public void initializeStatistic() {
        campaigns = 0;
        completeds = 0;
        sceneries = 0;
        guilds = 0;
        campaignWinners = 0;
        campaignDefeats = 0;
        campaignLeastDeaths = 0;
        campaignMostCoins = 0;
        campaignWonRewards = 0;
        campaignWonTitles = 0;
        sceneryWinners = 0;
        sceneryDefeats = 0;
        sceneryLeastDeaths = 0;
        sceneryMostCoins = 0;
        sceneryWonRewards = 0;
        sceneryWonTitles = 0;
    }

}
