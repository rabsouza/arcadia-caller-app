package br.com.battista.arcadiacaller;

import br.com.battista.arcadiacaller.service.AppService;

public class Inject {

    public static AppService provideAppService() {
        return new AppService();
    }
}
