package co.gosalo.androidreview.api;

import java.util.List;

import co.gosalo.androidreview.api.model.Event;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GosaloService {

    @GET("/events")
    Call<PagedResponseBody<List<Event>>> getEvents();
}
