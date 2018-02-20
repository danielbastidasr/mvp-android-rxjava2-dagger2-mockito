package co.gosalo.androidreview.activities.eventdetail;

import android.app.Activity;

import co.gosalo.androidreview.activities.eventdetail.mvp.EventDetailModel;
import co.gosalo.androidreview.activities.eventdetail.mvp.EventDetailPresenter;
import co.gosalo.androidreview.activities.eventdetail.mvp.EventDetailView;
import co.gosalo.androidreview.app.api.model.Event;
import dagger.Module;
import dagger.Provides;


@Module
public class EventDetailModule {

    private final EventDetailActivity activity;
    private final Event event;

    public EventDetailModule(EventDetailActivity activity, Event event) {
        this.activity = activity;
        this.event = event;
    }

    @Provides
    @EventDetailScope
    public EventDetailView view(){
        return new EventDetailView(activity);
    }

    @Provides
    @EventDetailScope
    public EventDetailModel model(){
        return new EventDetailModel(activity,event);
    }

    @Provides
    @EventDetailScope
    public EventDetailPresenter eventDetailPresenter(EventDetailView view,EventDetailModel model){
        return new EventDetailPresenter(view,model,activity);
    }

}
