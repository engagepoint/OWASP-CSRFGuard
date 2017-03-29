package org.owasp.csrfguard.config;

import org.owasp.csrfguard.config.overlay.ConfigurationAutodetectProviderFactory;
import org.owasp.csrfguard.config.overlay.ConfigurationOverlayProviderFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexander.serbin on 3/29/2017.
 */
public final class ConfigurationProviderFactoryMap {

    private static Map<String, Class<? extends ConfigurationProviderFactory>> map;

    private ConfigurationProviderFactoryMap() {}

    static {
        Map<String, Class<? extends ConfigurationProviderFactory>> aMap
                = new HashMap<String, Class<? extends ConfigurationProviderFactory>>();
        aMap.put(ConfigurationAutodetectProviderFactory.class.getName(), ConfigurationAutodetectProviderFactory.class);
        aMap.put(ConfigurationOverlayProviderFactory.class.getName(), ConfigurationOverlayProviderFactory.class);
        aMap.put(NullConfigurationProviderFactory.class.getName(), NullConfigurationProviderFactory.class);
        aMap.put(PropertiesConfigurationProviderFactory.class.getName(), PropertiesConfigurationProviderFactory.class);
        map = Collections.unmodifiableMap(aMap);
    }

    public static Class<? extends ConfigurationProviderFactory> get(String configurationProviderFactoryClassName) {
        return map.get(configurationProviderFactoryClassName);
    }
}
