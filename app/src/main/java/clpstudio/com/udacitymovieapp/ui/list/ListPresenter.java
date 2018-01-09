package clpstudio.com.udacitymovieapp.ui.list;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.config.mvp.BaseMvpPresenter;
import clpstudio.com.udacitymovieapp.data.MovieRepository;
import clpstudio.com.udacitymovieapp.data.model.Movie;
import clpstudio.com.udacitymovieapp.data.model.PopularMovieModel;
import io.reactivex.Single;

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
        getMostPopularMovies();
    }

    public void onMovieClicked(Movie popularMovie) {
        view().gotoDetailsPage(popularMovie);
    }

    public void onTopRatedClicked() {
        getTopRatedMovies();
    }

    public void onMostPopularClicked() {
        getMostPopularMovies();
    }

    private void getMostPopularMovies() {
        getMovies(movieRepository.getPopularMovies());
    }

    private void getTopRatedMovies() {
        getMovies(movieRepository.getTopRatedMovies());
    }

    private void getMovies(Single<PopularMovieModel> apiService) {
        view().showProgressBar();
        view().hideEmptyText();

        apiService.subscribe(res -> {
            view().showData(res.getResults());
            view().hideProgressBar();

            if (res.getResults() == null || res.getResults().isEmpty()) {
                view().showEmptyText();
            }
        }, throwable -> {
            if (throwable instanceof UnknownHostException) {
                view().showError(resources.getString(R.string.error_no_internet));
            } else {
                view().showError(resources.getString(R.string.error_something_went_wrong));
            }
            view().hideProgressBar();
        });
    }

    public interface View extends BaseMvpPresenter.View {
        void showData(List<Movie> movies);

        void showError(String error);

        void showProgressBar();

        void hideProgressBar();

        void showEmptyText();

        void hideEmptyText();

        void gotoDetailsPage(Movie popularMovie);
    }

}
