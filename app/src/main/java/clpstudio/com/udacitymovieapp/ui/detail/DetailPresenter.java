package clpstudio.com.udacitymovieapp.ui.detail;

import android.content.res.Resources;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.config.mvp.BaseMvpPresenter;
import clpstudio.com.udacitymovieapp.data.model.movie.Movie;
import clpstudio.com.udacitymovieapp.data.model.review.ReviewModel;
import clpstudio.com.udacitymovieapp.data.model.trailer.TrailerModel;
import clpstudio.com.udacitymovieapp.data.model.zip.ReviewTrailerModel;
import clpstudio.com.udacitymovieapp.data.repos.MovieRepository;
import clpstudio.com.udacitymovieapp.data.utils.UrlConstants;

public class DetailPresenter extends BaseMvpPresenter<DetailPresenter.View> {

    @Inject
    MovieRepository movieRepository;
    @Inject
    Resources resources;

    private String trailer1Key;

    @Inject
    public DetailPresenter() {
    }

    public void onDataLoaded(Movie popularMovie) {
        getReviewsAndTrailers(popularMovie);

        view().showTitle(popularMovie.getOriginalTitle());
        view().showReleaseDate(popularMovie.getReleaseDate());
        view().showDescription(popularMovie.getOverview());
        view().showRating(resources.getString(R.string.details_rating, String.valueOf(popularMovie.getVoteAverage())));
        showImage(popularMovie);
    }

    private void getReviewsAndTrailers(Movie popularMovie) {
        view().showProgressTrailersAndReviews();
        movieRepository.getReviews(popularMovie.getId())
                .zipWith(movieRepository.getTrailers(popularMovie.getId()), ReviewTrailerModel::new)
                .subscribe(reviewTrailerModel -> {
                    List<ReviewModel> reviews = reviewTrailerModel.getReview().getResults();
                    List<TrailerModel> trailers = reviewTrailerModel.getTrailer().getResults();

                    if (reviews != null && !reviews.isEmpty()) {
                        view().showReviewsTrailersSections();
                        view().showReviewTitle(reviews.get(0).getAuthor());
                        view().showReviewMessage(reviews.get(0).getContent());

                        if (reviews.size() <= 1) {
                            view().hideSeeAllReviewsButton();
                        }
                    } else {
                        view().hideSeeAllReviewsButton();
                        view().showReviewTitle(resources.getString(R.string.no_reviews));
                    }

                    if (trailers != null && !trailers.isEmpty()) {
                        view().showReviewsTrailersSections();
                        view().showTrailersContainer();
                        trailer1Key = trailers.get(0).getKey();

                        if (trailers.size() <= 1) {
                            view().hideSeeAllTrailersButton();
                        }
                    } else {
                        view().hideSeeAllTrailersButton();
                    }

                    view().hideProgressTrailersAndReviews();
                }, throwable -> {
                    if (throwable instanceof UnknownHostException) {
                        view().showError(resources.getString(R.string.error_no_internet));
                    } else {
                        view().showError(resources.getString(R.string.error_problem_getting_data));
                    }
                    view().hideProgressTrailersAndReviews();
                });
    }

    private void showImage(Movie popularMovie) {
        String apiKey = resources.getString(R.string.api_key);
        String url = UrlConstants.BASE_IMAGE_URL + popularMovie.getPosterPath() + UrlConstants.QUERY_APY_KEY + apiKey;
        view().showPoster(url);
    }

    public void onClickSeeAllReviews(Movie movie) {
        view().gotoReviewsListActivity(movie);
    }

    public void onClickSeeAllTrailer(Movie movie) {
        view().gotoTrailersListActivity(movie);
    }

    public void onClickTrailer1() {
        view().openYoutubeApp(trailer1Key);
    }

    public interface View extends BaseMvpPresenter.View {

        void showTitle(String title);

        void showReleaseDate(String date);

        void showRating(String rating);

        void showDescription(String description);

        void showPoster(String url);

        void showError(String text);

        void showProgressTrailersAndReviews();

        void hideProgressTrailersAndReviews();

        void gotoReviewsListActivity(Movie movie);

        void gotoTrailersListActivity(Movie movie);

        void showReviewsTrailersSections();

        void showReviewTitle(String title);

        void showReviewMessage(String message);

        void showTrailersContainer();

        void hideSeeAllTrailersButton();

        void hideSeeAllReviewsButton();

        void openYoutubeApp(String key);
    }

}
