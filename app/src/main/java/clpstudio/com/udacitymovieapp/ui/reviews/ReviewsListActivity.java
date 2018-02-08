package clpstudio.com.udacitymovieapp.ui.reviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import clpstudio.com.udacitymovieapp.Henson;
import clpstudio.com.udacitymovieapp.MovieApplication;
import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.data.model.movie.Movie;
import clpstudio.com.udacitymovieapp.data.model.review.ReviewModel;

public class ReviewsListActivity extends AppCompatActivity implements ReviewsListPresenter.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    View progressBar;
    @BindView(R.id.error_text)
    TextView errorText;

    @Inject
    ReviewsListPresenter presenter;

    @InjectExtra
    Movie movie;

    private ReviewsListAdapter adapter;

    public static void startActivity(Activity activity, Movie movie) {
        Intent intent = Henson.with(activity)
                .gotoReviewsListActivity()
                .movie(movie)
                .build();
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_list);

        ((MovieApplication) getApplicationContext()).getDiComponent().inject(this);
        ButterKnife.bind(this);
        Dart.inject(this);
        adapter = new ReviewsListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        presenter.bindView(this);
        presenter.onDataLoaded(movie);
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
        presenter.unbindView();
        super.onDestroy();
    }

    @Override
    public void showReviews(List<ReviewModel> data) {
        adapter.setAll(data);
    }

    @Override
    public void showError(String message) {
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(message);
    }

    @Override
    public void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        progressBar.setVisibility(View.GONE);
    }
}
