package com.vmos.config;

import java.util.HashMap;
import java.util.Map;

public enum Browser {

    CHROME("chrome");

    private static final Map<String, Browser> BY_LABEL = new HashMap<>();

    static {
        for (Browser browser : Browser.values()) {
            BY_LABEL.put(browser.label, browser);
        }
    }

    public final String label;

    Browser(String label) {
        this.label = label;
    }

    public static Browser parse(String label) {
        if (BY_LABEL.get(label) == null) {
            throw new IllegalStateException(String.format("%s is not a valid browser choice. Choose your browser from %s.", label, BY_LABEL.keySet()));
        } else {
            return BY_LABEL.get(label);
        }
    }
}
