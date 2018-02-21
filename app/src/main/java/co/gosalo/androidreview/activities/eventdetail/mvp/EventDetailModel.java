package co.gosalo.androidreview.activities.eventdetail.mvp;

import android.app.Activity;

import co.gosalo.androidreview.app.api.model.Event;
import io.reactivex.Observable;


public class EventDetailModel {

    private Observable<Event> event;

    public EventDetailModel( Event event) {
        this.event = Observable.just(event);
    }
    public Observable<Event> getEvent(){
        return event;
    }
}
