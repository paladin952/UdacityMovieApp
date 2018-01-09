package clpstudio.com.udacitymovieapp.config.CustomRxJava2CallAdapter;

import java.lang.reflect.Type;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Call;
import retrofit2.CallAdapter;

/**
 * Adapter that always return retrofit api calls on main thread
 */
public class CustomRxJava2CallAdapter  implements CallAdapter<Object, Object> {
    private final CallAdapter<Object, ?> delegate;

    public CustomRxJava2CallAdapter(CallAdapter<Object, ?> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Type responseType() {
        return delegate.responseType();
    }

    @Override
    public Object adapt(Call<Object> call) {
        Object result = delegate.adapt(call);
        if (result instanceof Completable) {
            return ((Completable) result)
                    .observeOn(AndroidSchedulers.mainThread());
        }
        if (result instanceof Flowable) {
            return ((Flowable<?>) result)
                    .observeOn(AndroidSchedulers.mainThread());
        }
        if (result instanceof Single) {
            return ((Single<?>) result)
                    .observeOn(AndroidSchedulers.mainThread());
        }
        if (result instanceof Maybe) {
            return ((Maybe<?>) result)
                    .observeOn(AndroidSchedulers.mainThread());
        }
        if (result instanceof Observable) {
            return ((Observable<?>) result)
                    .observeOn(AndroidSchedulers.mainThread());
        }
        throw new RuntimeException("Unsupported call type");
    }

}
