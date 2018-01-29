package co.gosalo.androidreview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.List;

import javax.inject.Inject;

import co.gosalo.androidreview.api.GosaloService;
import co.gosalo.androidreview.api.PagedResponseBody;
import co.gosalo.androidreview.api.model.Event;
import co.gosalo.androidreview.view.adapter.EventAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    private RecyclerView recyclerView;

    @Inject
    GosaloService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().inject(this);

        recyclerView = findViewById(R.id.reciclerView);
        recyclerView.setHasFixedSize(true);
        //          WE SET ORIENTATION OF THE RECYCLE VIEW AS VERTICAL
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //          WE ADD SOME SEPARATION AMONG THE EVENTS
        recyclerView.addItemDecoration(new EventAdapter.SpaceItems(30));

        getEvents();
    }

    public void getEvents() {
        service.getEvents().enqueue(new Callback<PagedResponseBody<List<Event>>>() {
            @Override
            public void onResponse(Call<PagedResponseBody<List<Event>>> call, Response<PagedResponseBody<List<Event>>> response) {

                //          CREATE AN ADAPTER
                EventAdapter eventAdapter = new EventAdapter(response.body().getContent());
                recyclerView.setAdapter(eventAdapter);

            }

            @Override
            public void onFailure(Call<PagedResponseBody<List<Event>>> call, Throwable t) {
                Log.i(LOG_TAG, "Gosalo call failed");
            }
        });
    }
}
