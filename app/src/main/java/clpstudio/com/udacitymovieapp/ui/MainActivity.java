package clpstudio.com.udacitymovieapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import clpstudio.com.udacitymovieapp.MovieApplication;
import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.data.model.PopularMovie;
import clpstudio.com.udacitymovieapp.ui.detail.DetailActivity;
import clpstudio.com.udacitymovieapp.ui.list.ListAdapter;
import clpstudio.com.udacitymovieapp.ui.list.ListPresenter;

public class MainActivity extends AppCompatActivity implements ListPresenter.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    View progressBar;
    @BindView(R.id.empty_text)
    TextView emptyText;

    @Inject
    ListPresenter listPresenter;

    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MovieApplication) getApplicationContext()).getDiComponent().inject(this);
        ButterKnife.bind(this);

        listAdapter = new ListAdapter(popularMovie -> listPresenter.onMovieClicked(popularMovie));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));

        listPresenter.bindView(this);
    }

    @Override
    protected void onDestroy() {
        listPresenter.unbindView();
        super.onDestroy();
    }

    @Override
    public void showData(List<PopularMovie> movies) {
        listAdapter.setAll(movies);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyText() {
        emptyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyText() {
        emptyText.setVisibility(View.GONE);
    }

    @Override
    public void gotoDetailsPage(PopularMovie popularMovie) {
        DetailActivity.startActivity(this, popularMovie);
    }
}
