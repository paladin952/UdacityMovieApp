package clpstudio.com.udacitymovieapp.ui.detail;

import clpstudio.com.udacitymovieapp.config.mvp.BaseMvpPresenter;
import clpstudio.com.udacitymovieapp.data.model.PopularMovie;

/**
 * Created by clapalucian on 09/01/2018.
 */

public class DetailPresenter extends BaseMvpPresenter<DetailPresenter.View> {

    public void onDataLoaded(PopularMovie popularMovie) {

    }

    public interface View extends BaseMvpPresenter.View {

    }

}
