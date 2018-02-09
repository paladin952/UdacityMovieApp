package clpstudio.com.udacitymovieapp.data.repos;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.util.Log;

import com.squareup.sqlbrite3.BriteContentResolver;

import java.util.List;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.data.contentprovider.MovieContentProvider;
import clpstudio.com.udacitymovieapp.data.database.DbContract;
import clpstudio.com.udacitymovieapp.data.model.movie.Movie;
import clpstudio.com.udacitymovieapp.data.model.movie.PopularMovieModel;
import clpstudio.com.udacitymovieapp.data.model.review.ReviewResponseModel;
import clpstudio.com.udacitymovieapp.data.model.trailer.TrailerResponseModel;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MovieRepository {

    @Inject
    ApiService apiService;
    @Inject
    Resources resources;
    @Inject
    BriteContentResolver briteContentResolver;
    @Inject
    ContentResolver contentResolver;

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

    public Single<TrailerResponseModel> getTrailers(int id) {
        return apiService.getTrailers(id, resources.getString(R.string.api_key));
    }

    public Observable<List<Movie>> getSaved() {
        briteContentResolver.createQuery(MovieContentProvider.CONTENT_URI, DbContract.Movie.PROJECTION(), null, null, null, true)
                .subscribeOn(Schedulers.io())
                .subscribe(query -> {
                    Log.d("luci", query.toString());
                });

        return null;
    }

    public void saveMovie(Movie movie) {
        AsyncQueryHandler handler = new AsyncQueryHandler(contentResolver) {};
        handler.startInsert(-1, null, MovieContentProvider.CONTENT_URI, movie.getContentValues());
    }

    public void deleteMovie(Movie movie) {
        String where = DbContract.Movie._ID + "=?";
        String[] args = new String[]{String.valueOf(movie.getId())};

        AsyncQueryHandler handler = new AsyncQueryHandler(contentResolver) {};
        handler.startDelete(-1, null, MovieContentProvider.CONTENT_URI, where, args);
    }



}
