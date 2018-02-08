package clpstudio.com.udacitymovieapp.ui.trailers;

import java.util.List;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.config.mvp.BaseMvpPresenter;
import clpstudio.com.udacitymovieapp.data.model.movie.Movie;
import clpstudio.com.udacitymovieapp.data.model.trailer.TrailerModel;
import clpstudio.com.udacitymovieapp.data.repos.MovieRepository;

public class TrailersListPresenter extends BaseMvpPresenter<TrailersListPresenter.View> {

    @Inject
    MovieRepository movieRepository;

    @Inject
    public TrailersListPresenter() {
    }

    public void onDataLoaded(Movie popularMovie) {
        view().showLoadingIndicator();
        movieRepository.getTrailers(popularMovie.getId())
                .subscribe(trailersResponse -> {
                    if (!trailersResponse.getResults().isEmpty()) {
                        view().showTrailers(trailersResponse.getResults());
                    } else {
                        //TODO
                    }
                    view().hideLoadingIndicator();
                }, throwable -> {
                    //TODO
                    view().hideLoadingIndicator();
                });
    }

    public interface View extends BaseMvpPresenter.View {
        void showTrailers(List<TrailerModel> data);

        void showEmptyData();

        void showLoadingIndicator();

        void hideLoadingIndicator();
    }

}
