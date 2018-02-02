package co.gosalo.androidreview.main.mvp;

import android.app.Activity;


import java.util.List;

import co.gosalo.androidreview.app.api.GosaloService;
import co.gosalo.androidreview.app.api.PagedResponseBody;
import co.gosalo.androidreview.app.api.model.Event;
import io.reactivex.Observable;



public class MainModel {

    private final Activity activity;
    private final GosaloService gosaloService;

    public MainModel(Activity activity, GosaloService gosaloService) {
        this.activity = activity;
        this.gosaloService = gosaloService;
    }


    public Observable<PagedResponseBody<List<Event>>> getListEvents(){
         return gosaloService.getEvents();
    }

}
