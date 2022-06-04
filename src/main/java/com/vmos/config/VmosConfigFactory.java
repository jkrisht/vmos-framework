package com.vmos.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class VmosConfigFactory {
    private static final VmosConfigFactory uniqueInstance = new VmosConfigFactory();

    private VmosConfigFactory() {
    }

    public static VmosConfigFactory getInstance() {
        return uniqueInstance;
    }

    public Config getConfig() {
        // Load default system properties and add config properties
        Config applicationConfig = ConfigFactory.load();
        Config choicesConfig = ConfigFactory.load("config");
        Config baseConfig = choicesConfig.withFallback(applicationConfig);
        return baseConfig;
    }
}
