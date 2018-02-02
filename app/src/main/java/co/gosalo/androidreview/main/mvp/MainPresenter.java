package co.gosalo.androidreview.main.mvp;

import android.util.Log;

import java.util.List;

import co.gosalo.androidreview.app.api.PagedResponseBody;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.main.mvp.view.MainActivityView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainPresenter {
    private final MainActivityView view;
    private final MainModel model;


    private Call<PagedResponseBody<List<Event>>> callback;

    public MainPresenter(MainActivityView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate(){
        view.showLoading(true);
        callback = model.getListEvents();

        callback.enqueue(new Callback<PagedResponseBody<List<Event>>>() {
            @Override
            public void onResponse(Call<PagedResponseBody<List<Event>>> call, Response<PagedResponseBody<List<Event>>> response) {

                view.showLoading(false);

                view.setUpRecyclerView(response.body().getContent());
                Log.i("WORKED", "Gosalo call success");


            }

            @Override
            public void onFailure(Call<PagedResponseBody<List<Event>>> call, Throwable t) {
                Log.i("DIDNTWORK", "Gosalo call failed");
            }
        });

    }

    public void onDestroy(){
        callback.cancel();
    }


}
