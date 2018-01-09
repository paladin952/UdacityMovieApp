package clpstudio.com.udacitymovieapp.config.dagger;


import javax.inject.Singleton;

import clpstudio.com.udacitymovieapp.MovieApplication;
import clpstudio.com.udacitymovieapp.config.dagger.modules.ApplicationModule;
import clpstudio.com.udacitymovieapp.config.dagger.modules.RetrofitModule;
import clpstudio.com.udacitymovieapp.ui.MainActivity;
import clpstudio.com.udacitymovieapp.ui.detail.DetailsActivity;
import dagger.Component;

@Component(
        modules = {
                ApplicationModule.class,
                RetrofitModule.class,
        }
)
@Singleton
public interface DIComponent {

    void inject(MovieApplication inject);

    void inject(MainActivity activity);

    void inject(DetailsActivity inject);

}
