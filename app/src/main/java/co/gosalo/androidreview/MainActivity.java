package co.gosalo.androidreview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import co.gosalo.androidreview.api.GosaloClient;
import co.gosalo.androidreview.api.GosaloService;
import co.gosalo.androidreview.api.PagedResponseBody;
import co.gosalo.androidreview.api.model.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getEvents();
    }

    public void getEvents() {

        GosaloService service = new GosaloClient().getGosaloService();

        service.getEvents().enqueue(new Callback<PagedResponseBody<List<Event>>>() {
            @Override
            public void onResponse(Call<PagedResponseBody<List<Event>>> call, Response<PagedResponseBody<List<Event>>> response) {
                Log.i(LOG_TAG, String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<PagedResponseBody<List<Event>>> call, Throwable t) {
                Log.i(LOG_TAG, "Gosalo call failed");
            }
        });
    }
}
