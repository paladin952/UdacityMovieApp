package clpstudio.com.udacitymovieapp.data.model.zip;

import clpstudio.com.udacitymovieapp.data.model.review.ReviewResponseModel;
import clpstudio.com.udacitymovieapp.data.model.trailer.TrailerResponseModel;

public class ReviewTrailerModel {

    private ReviewResponseModel review;
    private TrailerResponseModel trailer;

    public ReviewTrailerModel(ReviewResponseModel review, TrailerResponseModel trailer) {
        this.review = review;
        this.trailer = trailer;
    }

    public ReviewResponseModel getReview() {
        return review;
    }

    public TrailerResponseModel getTrailer() {
        return trailer;
    }
}
