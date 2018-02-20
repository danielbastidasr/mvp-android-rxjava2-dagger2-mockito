package co.gosalo.androidreview.app.api;

import java.util.List;

import co.gosalo.androidreview.app.api.model.Event;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GosaloService {

    @GET("/events")
    Observable<PagedResponseBody<List<Event>>> getEvents(@Query("page") Integer page);
}
