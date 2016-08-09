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
import br.com.battista.arcadiacaller.service.database.CardServiceFromDatabase;
import br.com.battista.arcadiacaller.service.database.HeroServiceFromDatabase;
import br.com.battista.arcadiacaller.service.database.SceneryServiceFromDatabase;
import br.com.battista.arcadiacaller.service.database.StatisticUserServiceFromDatabase;
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
        return provideHeroService(Boolean.TRUE);
    }

    public synchronized static HeroService provideHeroService(Boolean cached) {
        if (!cached && MainApplication.instance().checkOnlineServer()) {
            return new HeroServiceFromServer();
        }
        return new HeroServiceFromDatabase();
    }

    public synchronized static CardService provideCardService() {
        return provideCardService(Boolean.TRUE);
    }

    public synchronized static CardService provideCardService(Boolean cached) {
        if (!cached && MainApplication.instance().checkOnlineServer()) {
            return new CardServiceFromServer();
        }
        return new CardServiceFromDatabase();
    }

    public synchronized static SceneryService provideSceneryService() {
        return provideSceneryService(Boolean.TRUE);
    }

    public synchronized static SceneryService provideSceneryService(Boolean cached) {
        if (!cached && MainApplication.instance().checkOnlineServer()) {
            return new SceneryServiceFromServer();
        }
        return new SceneryServiceFromDatabase();
    }

    public synchronized static CampaignService provideCampaignService() {
        return new CampaignServiceFromServer();
    }

    public synchronized static StatisticUserService provideStatisticUserService() {
        return provideStatisticUserService(Boolean.TRUE);
    }

    public synchronized static StatisticUserService provideStatisticUserService(Boolean cached) {
        if (!cached && MainApplication.instance().checkOnlineServer()) {
            return new StatisticUserServiceFromServer();
        }
        return new StatisticUserServiceFromDatabase();
    }

    public synchronized static CampaignCompleteService provideCampaignCompleteService() {
        return new CampaignCompleteService();
    }

}
