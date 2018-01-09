package clpstudio.com.udacitymovieapp.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.config.glide.GlideRequestOptionUtils;
import clpstudio.com.udacitymovieapp.data.model.Movie;
import clpstudio.com.udacitymovieapp.data.utils.UrlConstants;

import static clpstudio.com.udacitymovieapp.data.utils.UrlConstants.QUERY_APY_KEY;

/**
 * Created by clapalucian on 09/01/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Movie> data = new ArrayList<>();
    private ViewHolder.OnClickMovieListener onClickMovieListener;

    public ListAdapter(ViewHolder.OnClickMovieListener onClickMovieListener) {
        this.onClickMovieListener = onClickMovieListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_row, parent, false);
        return new ViewHolder(view, onClickMovieListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setAll(List<Movie> movies) {
        data.clear();
        data.addAll(movies);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;

        private Movie popularMovie;

        public interface OnClickMovieListener {
            void onClickMovie(Movie popularMovie);
        }

        ViewHolder(View itemView, OnClickMovieListener onClickMovieListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            if (onClickMovieListener != null) {
                itemView.setOnClickListener(v -> onClickMovieListener.onClickMovie(popularMovie));
            }
        }

        void bind(Movie movie) {
            this.popularMovie = movie;
            String apiKey = itemView.getContext().getString(R.string.api_key);
            String url = UrlConstants.BASE_IMAGE_URL + movie.getPosterPath() + QUERY_APY_KEY + apiKey;
            Glide.with(itemView.getContext())
                    .load(url)
                    .apply(GlideRequestOptionUtils.getStandard())
                    .into(image);
        }
    }

}
