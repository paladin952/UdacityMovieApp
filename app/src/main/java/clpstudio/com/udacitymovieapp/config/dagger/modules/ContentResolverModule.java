package clpstudio.com.udacitymovieapp.config.dagger.modules;

import android.app.Application;
import android.content.ContentResolver;

import com.squareup.sqlbrite3.BriteContentResolver;
import com.squareup.sqlbrite3.SqlBrite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;

@Module
public class ContentResolverModule {

    @Provides
    @Singleton
    ContentResolver provideContentResolver(Application application) {
        return application.getContentResolver();
    }

    @Provides
    @Singleton
    BriteContentResolver provideBrideContentResolver(ContentResolver contentResolver) {
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        return sqlBrite.wrapContentProvider(contentResolver, Schedulers.io());
    }

}
