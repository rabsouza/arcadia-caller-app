package br.com.battista.arcadiacaller.service;

import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.SceneryCampaign;

public class CampaignCompleteService {

    public void finishCampaign(@NonNull Campaign campaign) {
        List<SceneryCampaign> sceneries = Lists.newArrayList(campaign.getScenery1(),
                campaign.getScenery2(),
                campaign.getScenery3(),
                campaign.getScenery4(),
                campaign.getScenery5(),
                campaign.getScenery6());

        List<String> activeGuilds = campaign.getAllActiveGuilds();

        campaign.setWinner(campaign.getSceneryCurrent().getWinner());

        campaign.setWinners(processWinners(sceneries, activeGuilds));
        campaign.setLeastDeaths(processLeastDeaths(sceneries, activeGuilds));
        campaign.setMostCoins(processMostCoins(sceneries, activeGuilds));
        campaign.setWonReward(processWonRewards(sceneries, activeGuilds));
        campaign.setWonTitle(processWonTitle(sceneries, activeGuilds));
    }

    private List<String> processWinners(List<SceneryCampaign> sceneries, List<String> activeGuilds) {
        List<String> winners = Lists.newLinkedList();
        Map<String, Integer> mapWinners = Maps.newLinkedHashMap();
        List<String> tempWinners = Lists.newArrayList();
        for (SceneryCampaign sceneryCampaign : sceneries) {
            if (sceneryCampaign.getWinner() != null) {
                tempWinners.add(sceneryCampaign.getWinner());
            }
        }

        int maxResult = 0;
        for (String guild : activeGuilds) {
            int frequency = Collections.frequency(tempWinners, guild);
            mapWinners.put(guild, frequency);
            if (frequency > maxResult) {
                maxResult = frequency;
            }
        }

        for (String guild : mapWinners.keySet()) {
            if (mapWinners.get(guild) == maxResult) {
                winners.add(guild);
            }
        }

        return winners;
    }

    private List<String> processLeastDeaths(List<SceneryCampaign> sceneries, List<String> activeGuilds) {
        List<String> leastDeaths = Lists.newLinkedList();

        Map<String, Integer> mapLeastDeaths = Maps.newLinkedHashMap();
        List<String> tempLeastDeaths = Lists.newArrayList();
        for (SceneryCampaign sceneryCampaign : sceneries) {
            if (sceneryCampaign.getLeastDeaths() != null) {
                tempLeastDeaths.addAll(sceneryCampaign.getLeastDeaths());
            }
        }

        int maxResult = 0;
        for (String guild : activeGuilds) {
            int frequency = Collections.frequency(tempLeastDeaths, guild);
            mapLeastDeaths.put(guild, frequency);
            if (frequency > maxResult) {
                maxResult = frequency;
            }
        }

        for (String guild : mapLeastDeaths.keySet()) {
            if (mapLeastDeaths.get(guild) == maxResult) {
                leastDeaths.add(guild);
            }
        }

        return leastDeaths;
    }

    private List<String> processMostCoins(List<SceneryCampaign> sceneries, List<String> activeGuilds) {
        List<String> mostCoins = Lists.newLinkedList();

        Map<String, Integer> mapMostCoins = Maps.newLinkedHashMap();
        List<String> tempMostCoins = Lists.newArrayList();
        for (SceneryCampaign sceneryCampaign : sceneries) {
            if (sceneryCampaign.getMostCoins() != null) {
                tempMostCoins.addAll(sceneryCampaign.getMostCoins());
            }
        }

        int maxResult = 0;
        for (String guild : activeGuilds) {
            int frequency = Collections.frequency(tempMostCoins, guild);
            mapMostCoins.put(guild, frequency);
            if (frequency > maxResult) {
                maxResult = frequency;
            }
        }

        for (String guild : mapMostCoins.keySet()) {
            if (mapMostCoins.get(guild) == maxResult) {
                mostCoins.add(guild);
            }
        }

        return mostCoins;
    }

    private List<String> processWonRewards(List<SceneryCampaign> sceneries, List<String> activeGuilds) {
        List<String> wonRewards = Lists.newLinkedList();

        Map<String, Integer> mapWonReward = Maps.newLinkedHashMap();
        List<String> tempWonReward = Lists.newArrayList();
        for (SceneryCampaign sceneryCampaign : sceneries) {
            if (sceneryCampaign.getWonReward() != null) {
                tempWonReward.addAll(sceneryCampaign.getWonReward());
            }
        }

        int maxResult = 0;
        for (String guild : activeGuilds) {
            int frequency = Collections.frequency(tempWonReward, guild);
            mapWonReward.put(guild, frequency);
            if (frequency > maxResult) {
                maxResult = frequency;
            }
        }

        for (String guild : mapWonReward.keySet()) {
            if (mapWonReward.get(guild) == maxResult) {
                wonRewards.add(guild);
            }
        }

        return wonRewards;
    }

    private List<String> processWonTitle(List<SceneryCampaign> sceneries, List<String> activeGuilds) {
        List<String> wonTitle = Lists.newLinkedList();

        Map<String, Integer> mapWonTitle = Maps.newLinkedHashMap();
        List<String> tempWonTitle = Lists.newArrayList();
        for (SceneryCampaign sceneryCampaign : sceneries) {
            if (sceneryCampaign.getWonTitle() != null) {
                tempWonTitle.addAll(sceneryCampaign.getWonTitle());
            }
        }

        int maxResult = 0;
        for (String guild : activeGuilds) {
            int frequency = Collections.frequency(tempWonTitle, guild);
            mapWonTitle.put(guild, frequency);
            if (frequency > maxResult) {
                maxResult = frequency;
            }
        }

        for (String guild : mapWonTitle.keySet()) {
            if (mapWonTitle.get(guild) == maxResult) {
                wonTitle.add(guild);
            }
        }

        return wonTitle;
    }

}
