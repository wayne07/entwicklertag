package de.seliger.togglz;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;

public enum AppFeatures implements Feature {
    
    @Label("First Feature")
    FEATURE_ONE,
    
    @Label("Second Feature")
    FEATURE_TWO,
    
    @Label("Third Feature")
    FEATURE_THREE;
    
    private final SingletonFeatureManagerProvider provider = new SingletonFeatureManagerProvider();

    public boolean isActive() {
        return provider.getFeatureManager().isActive( this );
    }

}
