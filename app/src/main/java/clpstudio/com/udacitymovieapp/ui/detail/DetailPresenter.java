package clpstudio.com.udacitymovieapp.ui.detail;

import android.content.res.Resources;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.config.mvp.BaseMvpPresenter;
import clpstudio.com.udacitymovieapp.data.model.Movie;
import clpstudio.com.udacitymovieapp.data.utils.UrlConstants;

/**
 * Created by clapalucian on 09/01/2018.
 */

public class DetailPresenter extends BaseMvpPresenter<DetailPresenter.View> {

    @Inject
    Resources resources;

    @Inject
    public DetailPresenter() {
    }

    public void onDataLoaded(Movie popularMovie) {
        view().showTitle(popularMovie.getOriginalTitle());
        view().showReleaseDate(popularMovie.getReleaseDate());
        view().showDescription(popularMovie.getOverview());
        view().showRating(resources.getString(R.string.details_rating, String.valueOf(popularMovie.getVoteAverage())));
        showImage(popularMovie);
    }

    private void showImage(Movie popularMovie){
        String apiKey = resources.getString(R.string.api_key);
        String url = UrlConstants.BASE_IMAGE_URL + popularMovie.getPosterPath() + UrlConstants.QUERY_APY_KEY + apiKey;
        view().showPoster(url);
    }

    public interface View extends BaseMvpPresenter.View {

        void showTitle(String title);

        void showReleaseDate(String date);

        void showRating(String rating);

        void showDescription(String description);

        void showPoster(String url);
    }

}
