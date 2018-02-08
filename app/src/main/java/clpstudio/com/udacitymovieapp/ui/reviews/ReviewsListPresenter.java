package clpstudio.com.udacitymovieapp.ui.reviews;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.config.mvp.BaseMvpPresenter;
import clpstudio.com.udacitymovieapp.data.model.movie.Movie;
import clpstudio.com.udacitymovieapp.data.model.review.ReviewModel;
import clpstudio.com.udacitymovieapp.data.repos.MovieRepository;

public class ReviewsListPresenter extends BaseMvpPresenter<ReviewsListPresenter.View> {

    @Inject
    MovieRepository movieRepository;
    @Inject
    Resources resources;

    @Inject
    public ReviewsListPresenter() {

    }

    @Override
    public void bindView(@NonNull View view) {
        super.bindView(view);
        //NOTE in future get data from cached local database
    }

    public void onDataLoaded(Movie popularMovie) {
        view().showLoadingIndicator();
        movieRepository.getReviews(popularMovie.getId())
                .subscribe(reviewResponseModel -> {
                    if (!reviewResponseModel.getResults().isEmpty()) {
                        view().showReviews(reviewResponseModel.getResults());
                    } else {
                        view().showError(resources.getString(R.string.empty_reviews_data));
                    }
                    view().hideLoadingIndicator();
                }, throwable -> {
                    if (throwable instanceof UnknownHostException) {
                        view().showError(resources.getString(R.string.error_no_internet));
                    } else {
                        view().showError(resources.getString(R.string.error_reviews_getting_data));
                    }
                    view().hideLoadingIndicator();
                });
    }

    public interface View extends BaseMvpPresenter.View {
        void showReviews(List<ReviewModel> data);

        void showError(String message);

        void showLoadingIndicator();

        void hideLoadingIndicator();
    }

}
