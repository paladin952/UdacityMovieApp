package clpstudio.com.udacitymovieapp.ui.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import clpstudio.com.udacitymovieapp.MovieApplication;
import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.config.glide.GlideRequestOptionUtils;
import clpstudio.com.udacitymovieapp.data.model.PopularMovie;

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

    @Inject
    DetailPresenter detailPresenter;

    @InjectExtra
    PopularMovie popularMovie;

    public static void startActivity(Activity activity, PopularMovie popularMovie) {
        Intent intent = Henson.with(activity)
                .gotoDetailsActivity()
                .popularMovie(popularMovie)
                .build();
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ((MovieApplication)getApplicationContext()).getDiComponent().inject(this);
        Dart.inject(this);

        detailPresenter.bindView(this);
        detailPresenter.onDataLoaded(popularMovie);
        setTitle(getString(R.string.details_screen_title));
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
}
