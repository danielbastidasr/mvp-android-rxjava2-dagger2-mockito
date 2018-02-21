package co.gosalo.androidreview.activities.main.mvp;


import java.util.List;

import co.gosalo.androidreview.app.api.GosaloService;
import co.gosalo.androidreview.app.api.PagedResponseBody;
import co.gosalo.androidreview.app.api.model.Event;
import io.reactivex.Observable;



public class MainModel {

    private final GosaloService gosaloService;

    public MainModel(GosaloService gosaloService) {
        this.gosaloService = gosaloService;
    }


    public Observable<PagedResponseBody<List<Event>>> getListEvents(Integer page){
         return gosaloService.getEvents(page);
    }

}
