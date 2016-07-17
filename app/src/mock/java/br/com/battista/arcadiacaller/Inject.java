package br.com.battista.arcadiacaller;

import br.com.battista.arcadiacaller.service.AppService;
import br.com.battista.arcadiacaller.service.FakeAppService;

public class Inject {

    public static AppService provideAppService() {
        return new FakeAppService();
    }
}
