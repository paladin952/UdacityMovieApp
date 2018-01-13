package clpstudio.com.udacitymovieapp.data.model.trailer;

import java.util.List;

/**
 * Created by clapalucian on 13/01/2018.
 */

public class TrailerResponseModel {

    private Integer id;
    private List<TrailerModel> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TrailerModel> getResults() {
        return results;
    }

    public void setResults(List<TrailerModel> results) {
        this.results = results;
    }

}
