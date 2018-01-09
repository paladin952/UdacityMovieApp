package clpstudio.com.udacitymovieapp.ui.list;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.config.mvp.BaseMvpPresenter;
import clpstudio.com.udacitymovieapp.data.MovieRepository;
import clpstudio.com.udacitymovieapp.data.model.PopularMovie;

/**
 * Created by clapalucian on 09/01/2018.
 */

public class ListPresenter extends BaseMvpPresenter<ListPresenter.View> {

    @Inject
    MovieRepository movieRepository;
    @Inject
    Resources resources;

    @Inject
    public ListPresenter() {
    }

    @Override
    public void bindView(@NonNull View view) {
        super.bindView(view);
        view().showProgressBar();
        view().hideEmptyText();
        movieRepository.getPopularMovies()
                .subscribe(res -> {
                    view().showData(res.getResults());
                    view().hideProgressBar();

                    if (res.getResults() == null || res.getResults().isEmpty()) {
                        view().showEmptyText();
                    }
                }, throwable -> {
                    view().showError(resources.getString(R.string.error_something_went_wrong));
                    view().hideProgressBar();
                });
    }

    public interface View extends BaseMvpPresenter.View {
        void showData(List<PopularMovie> movies);

        void showError(String error);

        void showProgressBar();

        void hideProgressBar();

        void showEmptyText();

        void hideEmptyText();
    }

}
