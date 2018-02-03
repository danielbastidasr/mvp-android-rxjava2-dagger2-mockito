package co.gosalo.androidreview.activities.eventdetail;

import android.app.Activity;

import co.gosalo.androidreview.activities.eventdetail.mvp.EventDetailView;
import dagger.Module;
import dagger.Provides;


@Module
public class EventDetailModule {

    private final Activity activity;

    public EventDetailModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @EventDetailScope
    public EventDetailView view(){
        return new EventDetailView(activity);
    }
/*
    @Provides
    @EventDetailScope
    public EventDetailModel model(){
        return new EventDetailModel(activity,gosaloService);
    }

    @Provides
    @EventDetailScope
    public EventDetailPresenter mainPresenter(EventDetailView view, EventDetailModel model){
        return new MainPresenter(view,model);
    }
*/
}
