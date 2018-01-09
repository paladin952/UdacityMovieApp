package clpstudio.com.udacitymovieapp.config.glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import clpstudio.com.udacitymovieapp.R;

/**
 * Created by clapalucian on 09/01/2018.
 */

public class GlideRequestOptionUtils {

    /**
     * Returning the most used request options
     */
    public static RequestOptions getStandard() {
        return new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.grey_4_50)
                .error(R.color.grey_4_50)
                .dontAnimate();
    }


}
