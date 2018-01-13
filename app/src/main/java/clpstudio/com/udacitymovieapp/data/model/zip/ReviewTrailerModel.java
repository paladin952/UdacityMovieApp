package clpstudio.com.udacitymovieapp.data.model.zip;

import clpstudio.com.udacitymovieapp.data.model.review.ReviewResponseModel;
import clpstudio.com.udacitymovieapp.data.model.trailer.TrailerModel;

public class ReviewTrailerModel {

    private ReviewResponseModel review;
    private TrailerModel trailer;

    public ReviewTrailerModel(ReviewResponseModel review, TrailerModel trailer) {
        this.review = review;
        this.trailer = trailer;
    }

    public ReviewResponseModel getReview() {
        return review;
    }

    public TrailerModel getTrailer() {
        return trailer;
    }
}
