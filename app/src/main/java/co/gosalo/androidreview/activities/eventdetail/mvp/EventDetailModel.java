package co.gosalo.androidreview.activities.eventdetail.mvp;

import android.app.Activity;

import co.gosalo.androidreview.activities.eventdetail.EventDetailActivity;
import co.gosalo.androidreview.app.api.model.Event;
import io.reactivex.Observable;


public class EventDetailModel {

    private final Activity activity;
    private Observable<Event> event;

    public EventDetailModel(Activity activity) {
        this.activity = activity;
        event = Observable.just(this.activity.getIntent().getParcelableExtra(EventDetailActivity.INTENT_DATA));
    }
    public Observable<Event> getEvent(){
        return event;
    }
}
