package clpstudio.com.udacitymovieapp.data.database;

import android.provider.BaseColumns;

import static clpstudio.com.udacitymovieapp.data.database.DbContract.Movie.COLUMN_MOVIE_ID;
import static clpstudio.com.udacitymovieapp.data.database.DbContract.Movie.COLUMN_NAME_ORIGINAL_TITLE;
import static clpstudio.com.udacitymovieapp.data.database.DbContract.Movie.COLUMN_NAME_OVERVIEW;
import static clpstudio.com.udacitymovieapp.data.database.DbContract.Movie.COLUMN_NAME_POSTER_PATH;
import static clpstudio.com.udacitymovieapp.data.database.DbContract.Movie.COLUMN_NAME_RELEASE_DATE;
import static clpstudio.com.udacitymovieapp.data.database.DbContract.Movie.COLUMN_NAME_VOTE_AVERAGE;

/**
 * Created by clapalucian on 09/02/2018.
 */

public final class DbContract {

    public static final String CREATE_ENTRIES =
            "CREATE TABLE " + Movie.TABLE_NAME + " (" +
                    Movie._ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_MOVIE_ID + " INTEGER," +
                    COLUMN_NAME_ORIGINAL_TITLE + " TEXT," +
                    COLUMN_NAME_POSTER_PATH + " TEXT," +
                    COLUMN_NAME_OVERVIEW + " TEXT," +
                    COLUMN_NAME_VOTE_AVERAGE + " REAL," +
                    COLUMN_NAME_RELEASE_DATE + " TEXT)";

    public static final String DELETE_MOVIE_TABLE = "DROP TABLE IF EXISTS + " + Movie.TABLE_NAME;


    private DbContract() {
    }

    public static class Movie implements BaseColumns {

        public static String[] PROJECTION() {
            return new String[]{
                    _ID,
                    COLUMN_MOVIE_ID,
                    COLUMN_NAME_ORIGINAL_TITLE,
                    COLUMN_NAME_POSTER_PATH,
                    COLUMN_NAME_OVERVIEW,
                    COLUMN_NAME_VOTE_AVERAGE,
                    COLUMN_NAME_RELEASE_DATE,
            };
        }

        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_NAME_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_NAME_POSTER_PATH = "poster_path";
        public static final String COLUMN_NAME_OVERVIEW = "overview";
        public static final String COLUMN_NAME_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_NAME_RELEASE_DATE = "release_date";

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.udacitymovieapp.movies";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.udacitymovieapp.movies";
    }
}
