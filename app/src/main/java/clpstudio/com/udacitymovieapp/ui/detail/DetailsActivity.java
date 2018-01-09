package clpstudio.com.udacitymovieapp.ui.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.f2prateek.dart.InjectExtra;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.data.model.PopularMovie;

public class DetailsActivity extends AppCompatActivity implements DetailPresenter.View {

    @Inject
    DetailPresenter detailPresenter;

    @InjectExtra
    PopularMovie popularMovie;

    public static void startActivity(Activity activity, PopularMovie popularMovie) {
        Intent intent = Henson.with(activity)
                .gotoDetailActivity()
                .popularMovie(popularMovie)
                .build();
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailPresenter.bindView(this);
        setTitle(getString(R.string.details_screen_title));
    }

    @Override
    protected void onDestroy() {
        detailPresenter.unbindView();
        super.onDestroy();
    }
}
