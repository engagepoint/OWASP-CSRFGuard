package org.owasp.csrfguard.config;

import org.owasp.csrfguard.config.overlay.ConfigurationAutodetectProviderFactory;
import org.owasp.csrfguard.config.overlay.ConfigurationOverlayProviderFactory;
import org.owasp.csrfguard.util.ClassesMapHolder;

/**
 * Created by alexander.serbin on 3/29/2017.
 */
public final class ConfigurationProviderFactoryClassesMapHolder extends ClassesMapHolder<ConfigurationProviderFactory> {

    public final static ConfigurationProviderFactoryClassesMapHolder INSTANCE = new ConfigurationProviderFactoryClassesMapHolder();

    private ConfigurationProviderFactoryClassesMapHolder() {
        init(ConfigurationAutodetectProviderFactory.class, ConfigurationOverlayProviderFactory.class,
                NullConfigurationProviderFactory.class, PropertiesConfigurationProviderFactory.class);
    }

}


