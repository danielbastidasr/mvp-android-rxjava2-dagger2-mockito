package co.gosalo.androidreview.activities.eventdetail;

import co.gosalo.androidreview.app.AppComponent;
import dagger.Component;
import dagger.Subcomponent;

@EventDetailScope
@Subcomponent(modules = { EventDetailModule.class })
public interface EventDetailComponent {

    void inject(EventDetailActivity eventDetailActivity);

}
