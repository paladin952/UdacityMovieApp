package clpstudio.com.udacitymovieapp.config.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import clpstudio.com.udacitymovieapp.MovieApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by clapalucian on 08/01/2018.
 */

@Module
public class ApplicationModule {

    Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    MovieApplication providesCustomApplication() {
        return (MovieApplication) application;
    }

    @Provides
    @Singleton
    Resources providesResources() {
        return application.getResources();
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application.getApplicationContext();
    }

}
