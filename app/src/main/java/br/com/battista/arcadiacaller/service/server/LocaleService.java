package br.com.battista.arcadiacaller.service.server;

import static br.com.battista.arcadia.caller.constants.LocaleConstant.LOCALE_EN;
import static br.com.battista.arcadia.caller.constants.LocaleConstant.LOCALE_PT;

import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LocaleService {

    private Map<String, Locale> supportedLocales;

    @PostConstruct
    public void init() {
        supportedLocales = Maps.newHashMap();
        supportedLocales.put(LOCALE_PT, new Locale(LOCALE_PT));
        supportedLocales.put(LOCALE_EN, new Locale(LOCALE_EN));
    }

    public Locale processSupportedLocales(String locale) {
        if (Strings.isNullOrEmpty(locale) || !supportedLocales.containsKey(locale.toLowerCase())) {
            return null;
        }
        return supportedLocales.get(locale.toLowerCase());
    }

}
