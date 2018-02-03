package co.gosalo.androidreview.activities.eventdetail;

import co.gosalo.androidreview.app.AppComponent;
import dagger.Component;

@EventDetailScope
@Component(modules = { EventDetailModule.class }, dependencies = AppComponent.class)
public interface EventDetailComponent {

    void inject(EventDetailActivity eventDetailActivity);

}
