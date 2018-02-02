package co.gosalo.androidreview.app.api;

import java.util.List;

import co.gosalo.androidreview.app.api.model.Event;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GosaloService {

    @GET("/events")
    Call<PagedResponseBody<List<Event>>> getEvents();
}
