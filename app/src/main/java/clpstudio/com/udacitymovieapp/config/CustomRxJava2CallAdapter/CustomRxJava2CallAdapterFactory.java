package clpstudio.com.udacitymovieapp.config.CustomRxJava2CallAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class CustomRxJava2CallAdapterFactory extends CallAdapter.Factory {
    private final CallAdapter.Factory delegate;

    public static CustomRxJava2CallAdapterFactory create(CallAdapter.Factory delegate) {
        return new CustomRxJava2CallAdapterFactory(delegate);
    }

    private CustomRxJava2CallAdapterFactory(CallAdapter.Factory delegate) {
        this.delegate = delegate;
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        if (rawType != Completable.class && rawType != Flowable.class && rawType != Single.class
                && rawType != Maybe.class && rawType != Observable.class) {
            return null;
        }
        return new CustomRxJava2CallAdapter((CallAdapter<Object, ?>) delegate.get(returnType, annotations, retrofit));
    }
}
