package clpstudio.com.udacitymovieapp.config.dagger.modules;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.data.repos.ApiService;
import clpstudio.com.udacitymovieapp.config.CustomRxJava2CallAdapter.CustomRxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ApplicationModule.class)
public class RetrofitModule {

    private static final String PROTOCOL = "https://";
    private static final int DEFAULT_TIMEOUT_SECONDS = 30;

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();
    }


    @Provides
    @Singleton
    public Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient okHttpClient) {
        return builder.client(okHttpClient).build();
    }


    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofitBuilder(Gson gson, Context context) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CustomRxJava2CallAdapterFactory.create(
                        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())))
                .baseUrl(context.getString(R.string.backend_hostname));
    }
}