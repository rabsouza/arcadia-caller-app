package br.com.battista.arcadiacaller.service.server;

import static br.com.battista.arcadiacaller.service.server.LocaleService.SupportedLocale.EN;
import static br.com.battista.arcadiacaller.service.server.LocaleService.SupportedLocale.PT;

import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;

public class LocaleService {

    public enum SupportedLocale {
        PT, EN;
    }

    private Map<String, String> supportedLocales;

    public LocaleService() {
        supportedLocales = Maps.newHashMap();
        supportedLocales.put(PT.name().toLowerCase(), PT.name().toLowerCase());
        supportedLocales.put(EN.name().toLowerCase(), EN.name().toLowerCase());
    }

    public String processSupportedLocales(Locale locale) {
        if (locale == null || !supportedLocales.containsKey(locale.getLanguage().toLowerCase())) {
            return EN.name();
        }
        return supportedLocales.get(locale.getLanguage().toLowerCase());
    }

}
