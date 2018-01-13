package clpstudio.com.udacitymovieapp.ui.trailers;

import javax.inject.Inject;

import clpstudio.com.udacitymovieapp.config.mvp.BaseMvpPresenter;

public class TrailersListPresenter extends BaseMvpPresenter<TrailersListPresenter.View> {

    @Inject
    public TrailersListPresenter() {
    }

    public interface View extends BaseMvpPresenter.View {

    }

}
