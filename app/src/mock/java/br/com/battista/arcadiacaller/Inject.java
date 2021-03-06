package br.com.battista.arcadiacaller;

import br.com.battista.arcadiacaller.service.AppService;
import br.com.battista.arcadiacaller.service.CampaignService;
import br.com.battista.arcadiacaller.service.CampainhaCompleteService;
import br.com.battista.arcadiacaller.service.CardService;
import br.com.battista.arcadiacaller.service.HeroService;
import br.com.battista.arcadiacaller.service.LoginService;
import br.com.battista.arcadiacaller.service.SceneryService;
import br.com.battista.arcadiacaller.service.StatisticUserService;
import br.com.battista.arcadiacaller.service.UserService;

public class Inject {

    public synchronized static AppService provideAppService() {
        return new AppService();
    }

    public synchronized static LoginService provideLoginService() {
        return new LoginService();
    }

    public synchronized static UserService provideUserService() {
        return new UserService();
    }

    public synchronized static HeroService provideHeroService() {
        return new HeroService();
    }

    public synchronized static CardService provideCardService() {
        return new CardService();
    }

    public synchronized static SceneryService provideSceneryService() {
        return new SceneryService();
    }

    public synchronized static CampaignService provideCampaignService() {
        return new CampaignService();
    }

    public synchronized static StatisticUserService provideStatisticUserService() {
        return new StatisticUserService();
    }

    public synchronized static CampainhaCompleteService provideCampainhaCompleteService() {
        return new CampainhaCompleteService();
    }

}
