package br.com.battista.arcadiacaller;

import br.com.battista.arcadiacaller.service.AppService;
import br.com.battista.arcadiacaller.service.CampaignCompleteService;
import br.com.battista.arcadiacaller.service.CampaignService;
import br.com.battista.arcadiacaller.service.CardService;
import br.com.battista.arcadiacaller.service.HeroService;
import br.com.battista.arcadiacaller.service.LoginService;
import br.com.battista.arcadiacaller.service.SceneryService;
import br.com.battista.arcadiacaller.service.StatisticUserService;
import br.com.battista.arcadiacaller.service.UserService;
import br.com.battista.arcadiacaller.service.server.AppServiceFromServer;
import br.com.battista.arcadiacaller.service.server.CampaignServiceFromServer;
import br.com.battista.arcadiacaller.service.server.CardServiceFromServer;
import br.com.battista.arcadiacaller.service.server.HeroServiceFromServer;
import br.com.battista.arcadiacaller.service.server.LoginServiceFromServer;
import br.com.battista.arcadiacaller.service.server.SceneryServiceFromServer;
import br.com.battista.arcadiacaller.service.server.StatisticUserServiceFromServer;
import br.com.battista.arcadiacaller.service.server.UserServiceFromServer;

public class Inject {

    public synchronized static AppService provideAppService() {
        return new AppServiceFromServer();
    }

    public synchronized static LoginService provideLoginService() {
        return new LoginServiceFromServer();
    }

    public synchronized static UserService provideUserService() {
        return new UserServiceFromServer();
    }

    public synchronized static HeroService provideHeroService() {
        return new HeroServiceFromServer();
    }

    public synchronized static CardService provideCardService() {
        return new CardServiceFromServer();
    }

    public synchronized static SceneryService provideSceneryService() {
        return new SceneryServiceFromServer();
    }

    public synchronized static CampaignService provideCampaignService() {
        return new CampaignServiceFromServer();
    }

    public synchronized static StatisticUserService provideStatisticUserService() {
        return new StatisticUserServiceFromServer();
    }

    public synchronized static CampaignCompleteService provideCampaignCompleteService() {
        return new CampaignCompleteService();
    }

}
