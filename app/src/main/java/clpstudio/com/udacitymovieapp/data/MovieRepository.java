package clpstudio.com.udacitymovieapp.data;

import android.content.res.Resources;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.bussines.ApiService;
import clpstudio.com.udacitymovieapp.data.model.PopularMovieModel;
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

    public Single<String> getTopRatedMovies() {
        return apiService.getTopRatedMovies(resources.getString(R.string.api_key));
    }
}
