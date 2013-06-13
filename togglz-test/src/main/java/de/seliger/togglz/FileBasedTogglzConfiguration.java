package de.seliger.togglz;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.file.FileBasedStateRepository;
import org.togglz.core.user.NoOpUserProvider;
import org.togglz.core.user.UserProvider;

public class FileBasedTogglzConfiguration implements TogglzConfig {

    @Override
    public StateRepository getStateRepository() {
        try {
            URL resource = FileBasedTogglzConfiguration.class.getResource( "features.properties" );
            File file = new File( resource.toURI() );
            return new FileBasedStateRepository( file );
        } catch ( URISyntaxException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Class<? extends Feature> getFeatureClass() {
        return AppFeatures.class;
    }

    @Override
    public UserProvider getUserProvider() {
        return new NoOpUserProvider();
    }

}