
package clpstudio.com.udacitymovieapp.data.model.movie;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

import clpstudio.com.udacitymovieapp.data.database.DbContract;

@Parcel
public class Movie {

    @SerializedName("vote_count")
    Integer voteCount;

    @SerializedName("id")
    Integer id;
    @SerializedName("video")
    Boolean video;

    @SerializedName("vote_average")
    Double voteAverage;

    String title;

    Double popularity;

    @SerializedName("poster_path")
    String posterPath;

    @SerializedName("original_language")
    String originalLanguage;

    @SerializedName("original_title")
    String originalTitle;

    @SerializedName("genre_ids")
    List<Integer> genreIds = null;

    @SerializedName("backdrop_path")
    String backdropPath;

    Boolean adult;

    String overview;

    @SerializedName("release_date")
    String releaseDate;

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.Movie.COLUMN_MOVIE_ID, getId());
        contentValues.put(DbContract.Movie.COLUMN_NAME_ORIGINAL_TITLE, getOriginalLanguage());
        contentValues.put(DbContract.Movie.COLUMN_NAME_OVERVIEW, getOverview());
        contentValues.put(DbContract.Movie.COLUMN_NAME_POSTER_PATH, getPosterPath());
        contentValues.put(DbContract.Movie.COLUMN_NAME_RELEASE_DATE, getReleaseDate());
        contentValues.put(DbContract.Movie.COLUMN_NAME_VOTE_AVERAGE, getVoteAverage());
        return null;
    }
}
