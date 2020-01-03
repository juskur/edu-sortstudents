package edu.sortstudents.model.impl.sourceloaders;

import edu.sortstudents.model.SourceLoader;

import java.util.HashMap;
import java.util.Map;

public abstract class SourceLoaderConfigurable implements SourceLoader {
    private Map<String, String> config;

    public SourceLoaderConfigurable(Map<String, String> config) {
        this.config = config;
    }

    public Map<String, String> getConfig() {
        return config;
    }
}
