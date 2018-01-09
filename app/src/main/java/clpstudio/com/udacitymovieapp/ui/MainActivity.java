package clpstudio.com.udacitymovieapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import clpstudio.com.udacitymovieapp.MovieApplication;
import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.data.model.PopularMovie;
import clpstudio.com.udacitymovieapp.ui.list.ListPresenter;

public class MainActivity extends AppCompatActivity implements ListPresenter.View {

    @Inject
    ListPresenter listPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MovieApplication) getApplicationContext()).getDiComponent().inject(this);
        ButterKnife.bind(this);

        listPresenter.bindView(this);
    }

    @Override
    protected void onDestroy() {
        listPresenter.unbindView();
        super.onDestroy();
    }

    @Override
    public void showData(List<PopularMovie> movies) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
