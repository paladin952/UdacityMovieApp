package clpstudio.com.udacitymovieapp.data.repos;

import clpstudio.com.udacitymovieapp.data.model.movie.PopularMovieModel;
import clpstudio.com.udacitymovieapp.data.model.review.ReviewResponseModel;
import clpstudio.com.udacitymovieapp.data.model.trailer.TrailerResponseModel;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/popular")
    Single<PopularMovieModel> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Single<PopularMovieModel> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Single<TrailerResponseModel> getTrailers(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Single<ReviewResponseModel> getReviews(@Path("id") int id, @Query("api_key") String apiKey);

}
