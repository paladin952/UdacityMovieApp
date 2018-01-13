package clpstudio.com.udacitymovieapp.ui.reviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.f2prateek.dart.HensonNavigable;

import javax.inject.Inject;

import butterknife.ButterKnife;
import clpstudio.com.udacitymovieapp.Henson;
import clpstudio.com.udacitymovieapp.MovieApplication;
import clpstudio.com.udacitymovieapp.R;

@HensonNavigable
public class ReviewsListActivity extends AppCompatActivity implements ReviewsListPresenter.View {

    @Inject
    ReviewsListPresenter presenter;

    public static void startActivity(Activity activity) {
        Intent intent = Henson.with(activity)
                .gotoReviewsListActivity()
                .build();
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_list);

        ((MovieApplication) getApplicationContext()).getDiComponent().inject(this);
        ButterKnife.bind(this);

        presenter.bindView(this);
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
}
