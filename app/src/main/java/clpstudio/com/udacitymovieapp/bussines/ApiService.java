package clpstudio.com.udacitymovieapp.bussines;

import clpstudio.com.udacitymovieapp.data.model.PopularMovieModel;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/popular")
    Single<PopularMovieModel> getPopularMovies(@Query("api_key")String apiKey);

    @GET("movie/top_rated")
    Single<PopularMovieModel> getTopRatedMovies(@Query("api_key")String apiKey);

}
