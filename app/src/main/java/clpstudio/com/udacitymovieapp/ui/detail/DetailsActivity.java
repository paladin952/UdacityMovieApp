package clpstudio.com.udacitymovieapp.ui.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import clpstudio.com.udacitymovieapp.Henson;
import clpstudio.com.udacitymovieapp.MovieApplication;
import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.config.glide.GlideRequestOptionUtils;
import clpstudio.com.udacitymovieapp.data.model.movie.Movie;
import clpstudio.com.udacitymovieapp.ui.reviews.ReviewsListActivity;
import clpstudio.com.udacitymovieapp.ui.trailers.TrailersListActivity;

public class DetailsActivity extends AppCompatActivity implements DetailPresenter.View {

    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.release_year)
    TextView releaseYearView;
    @BindView(R.id.rating)
    TextView ratingView;
    @BindView(R.id.description)
    TextView descriptionView;
    @BindView(R.id.poster)
    ImageView posterImage;
    @BindView(R.id.review_title)
    TextView review1Title;
    @BindView(R.id.review_message)
    TextView review1Message;

    @Inject
    DetailPresenter detailPresenter;

    @InjectExtra
    Movie popularMovie;

    public static void startActivity(Activity activity, Movie popularMovie) {
        Intent intent = Henson.with(activity)
                .gotoDetailsActivity()
                .popularMovie(popularMovie)
                .build();
        activity.startActivity(intent);
    }

    @OnClick(R.id.see_all_review_button)
    public void onClickSeeAllReviews() {
        detailPresenter.onClickSeeAllReviews();
    }

    @OnClick(R.id.see_all_trailers)
    public void onClickSeeAllTrailers() {
        detailPresenter.onClickSeeAllTrailer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ((MovieApplication)getApplicationContext()).getDiComponent().inject(this);
        Dart.inject(this);

        detailPresenter.bindView(this);
        detailPresenter.onCreate();
        detailPresenter.onDataLoaded(popularMovie);
        setTitle(getString(R.string.details_screen_title));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        detailPresenter.unbindView();
        super.onDestroy();
    }

    @Override
    public void showTitle(String title) {
        titleView.setText(title);
    }

    @Override
    public void showReleaseDate(String date) {
        releaseYearView.setText(date);
    }

    @Override
    public void showRating(String rating) {
        ratingView.setText(rating);
    }

    @Override
    public void showDescription(String description) {
        descriptionView.setText(description);
    }

    @Override
    public void showPoster(String url) {
        Glide.with(this)
                .load(url)
                .apply(GlideRequestOptionUtils.getStandard())
                .into(posterImage);
    }

    @Override
    public void showError(String text) {

    }

    @Override
    public void showProgressTrailersAndReviews() {

    }

    @Override
    public void hideProgressTrailersAndReviews() {

    }

    @Override
    public void gotoReviewsListActivity() {
        ReviewsListActivity.startActivity(this);
    }

    @Override
    public void gotoTrailersListActivity() {
        TrailersListActivity.startActivity(this);
    }
}
