package clpstudio.com.udacitymovieapp.data.contentprovider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import clpstudio.com.udacitymovieapp.data.database.DbContract;
import clpstudio.com.udacitymovieapp.data.database.DbHelper;


public class MovieContentProvider extends android.content.ContentProvider {

    private SQLiteDatabase db;

    private static final String PROVIDER_NAME = "clpstudio.com.udacitymovieapp.MovieContentProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/movies";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    static final int MOVIES = 1;
    static final int MOVIE_ID = 2;

    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "movies", MOVIES);
        uriMatcher.addURI(PROVIDER_NAME, "movies/#", MOVIE_ID);
    }

    @Override
    public boolean onCreate() {
        SQLiteOpenHelper dbHelper = new DbHelper(getContext());

        db = dbHelper.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int match = uriMatcher.match(uri);
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DbContract.Movie.TABLE_NAME);
        switch (match) {
            case MOVIES:

                break;
            case MOVIE_ID:
                qb.appendWhere(DbContract.Movie._ID + "=" + uri.getPathSegments().get(1));
                break;
            default:

        }
        Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                return DbContract.Movie.CONTENT_TYPE;
            case MOVIE_ID:
                return DbContract.Movie.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowId = db.insert(DbContract.Movie.TABLE_NAME, "", values);

        if (rowId > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count;
        switch (uriMatcher.match(uri)) {
            case MOVIES:
                count = db.delete(DbContract.Movie.TABLE_NAME, selection, selectionArgs);
                break;
            case MOVIE_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(DbContract.Movie.TABLE_NAME, DbContract.Movie._ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case MOVIES:
                count = db.update(DbContract.Movie.TABLE_NAME, values, selection, selectionArgs);
                break;

            case MOVIE_ID:
                count = db.update(DbContract.Movie.TABLE_NAME, values,
                        DbContract.Movie._ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
