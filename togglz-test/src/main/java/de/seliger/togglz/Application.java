package de.seliger.togglz;

public class Application {

    public static void start() {
        System.out.println( "start ..." );
        System.out.println( "available features:" );

        for ( AppFeatures feature : AppFeatures.values() ) {
            String featureName=feature.name();
            boolean isActive=feature.isActive();
            System.out.println( String.format( "Feature '%s' ist aktiv %s ", featureName, isActive ) );
        }

        if ( AppFeatures.FEATURE_ONE.isActive() ) {
            FeatureOne.run();
        }
        if ( AppFeatures.FEATURE_TWO.isActive() ) {
            FeatureTwo.run();
        }
        if ( AppFeatures.FEATURE_THREE.isActive() ) {
            FeatureThree.run();
        }
    }

    public static void stop() {
        System.out.println( "stop." );
    }

}
