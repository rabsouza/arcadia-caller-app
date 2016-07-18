package br.com.battista.arcadiacaller;

import br.com.battista.arcadiacaller.service.AppService;
import br.com.battista.arcadiacaller.service.LoginService;
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
}
