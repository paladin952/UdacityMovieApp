package clpstudio.com.udacitymovieapp.ui.trailers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import clpstudio.com.udacitymovieapp.R;
import clpstudio.com.udacitymovieapp.data.model.trailer.TrailerModel;

public class TrailersListAdapter extends RecyclerView.Adapter<TrailersListAdapter.ViewHolder> {

    private List<TrailerModel> data = new ArrayList<>();
    private ViewHolder.OnClickTrailerListener onClickTrailerListener;

    public TrailersListAdapter(ViewHolder.OnClickTrailerListener onClickTrailerListener) {
        this.onClickTrailerListener = onClickTrailerListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailers_list_row, parent, false);

        return new ViewHolder(view, onClickTrailerListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setAll(List<TrailerModel> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public interface OnClickTrailerListener {
            void onClickTrailer(TrailerModel trailer);
        }

        @BindView(R.id.title)
        TextView title;

        private TrailerModel trailerModel;

        public ViewHolder(View itemView,OnClickTrailerListener onClickTrailerListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                if (onClickTrailerListener != null) {
                    onClickTrailerListener.onClickTrailer(trailerModel);
                }
            });
        }

        public void bind(TrailerModel model, int position) {
            trailerModel = model;
            title.setText(itemView.getContext().getString(R.string.trailer_x, position + 1));
        }
    }
}
