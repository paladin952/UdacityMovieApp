package clpstudio.com.udacitymovieapp.data.repos;

import android.content.res.Resources;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.data.model.movie.PopularMovieModel;
import clpstudio.com.udacitymovieapp.data.model.review.ReviewResponseModel;
import clpstudio.com.udacitymovieapp.data.model.trailer.TrailerModel;
import io.reactivex.Single;

public class MovieRepository {

    @Inject
    ApiService apiService;
    @Inject
    Resources resources;

    @Inject
    public MovieRepository() {
    }

    public Single<PopularMovieModel> getPopularMovies() {
        return apiService.getPopularMovies(resources.getString(R.string.api_key));
    }

    public Single<PopularMovieModel> getTopRatedMovies() {
        return apiService.getTopRatedMovies(resources.getString(R.string.api_key));
    }

    public Single<ReviewResponseModel> getReviews(int id) {
        return apiService.getReviews(id, resources.getString(R.string.api_key));
    }

    public Single<TrailerModel> getTrailers(int id) {
        return apiService.getTrailers(id, resources.getString(R.string.api_key));
    }
}
