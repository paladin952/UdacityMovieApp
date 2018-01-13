package clpstudio.com.udacitymovieapp.ui.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.review_trailers_section)
    LinearLayout reviewTrailersSection;
    @BindView(R.id.progress_bar)
    View progressBar;
    @BindView(R.id.trailer_1)
    LinearLayout trailer1;
    @BindView(R.id.trailers_container)
    LinearLayout trailersContainer;
    @BindView(R.id.see_all_trailers)
    Button seeAllTrailersButton;

    @BindView(R.id.see_all_review_button)
    Button seeAllReviewsButton;

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

    @OnClick(R.id.trailer_1_play_button)
    public void onClickTrailer1() {
        detailPresenter.onClickTrailer1();
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
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressTrailersAndReviews() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressTrailersAndReviews() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void gotoReviewsListActivity() {
        ReviewsListActivity.startActivity(this);
    }

    @Override
    public void gotoTrailersListActivity() {
        TrailersListActivity.startActivity(this);
    }

    @Override
    public void showReviewsTrailersSections() {
        reviewTrailersSection.setVisibility(View.VISIBLE);
    }

    @Override
    public void showReviewTitle(String title) {
        review1Title.setText(title);
    }

    @Override
    public void showReviewMessage(String message) {
        review1Message.setText(message);
    }

    @Override
    public void showTrailersContainer() {
        trailersContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSeeAllTrailersButton() {
        seeAllTrailersButton.setVisibility(View.GONE);
    }

    @Override
    public void hideSeeAllReviewsButton() {
        seeAllReviewsButton.setVisibility(View.GONE);
    }
}
