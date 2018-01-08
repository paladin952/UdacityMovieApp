package clpstudio.com.udacitymovieapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import clpstudio.com.udacitymovieapp.config.dagger.DIComponent;
import clpstudio.com.udacitymovieapp.config.dagger.DaggerDIComponent;
import clpstudio.com.udacitymovieapp.config.dagger.modules.ApplicationModule;

public class MovieApplication extends Application {

    private DIComponent diComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        diComponent = DaggerDIComponent.builder().applicationModule(new ApplicationModule(this)).build();
        diComponent.inject(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
