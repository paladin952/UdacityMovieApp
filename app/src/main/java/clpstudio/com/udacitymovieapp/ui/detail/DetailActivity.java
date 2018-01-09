package clpstudio.com.udacitymovieapp.ui.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.f2prateek.dart.InjectExtra;

import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.data.model.PopularMovie;

public class DetailActivity extends AppCompatActivity {

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
    }
}
