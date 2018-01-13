package clpstudio.com.udacitymovieapp.ui.reviews;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.config.mvp.BaseMvpPresenter;

public class ReviewsListPresenter extends BaseMvpPresenter<ReviewsListPresenter.View> {


    @Inject
    public ReviewsListPresenter() {

    }

    @Override
    public void bindView(@NonNull View view) {
        super.bindView(view);

        //TODO get data from cached database and show it on screen
    }

    public interface View extends BaseMvpPresenter.View {

    }

}
