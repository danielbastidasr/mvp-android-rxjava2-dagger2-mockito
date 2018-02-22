package co.gosalo.androidreview.app;

import android.content.Context;


import co.gosalo.androidreview.activities.eventdetail.EventDetailComponent;
import co.gosalo.androidreview.activities.eventdetail.EventDetailModule;
import co.gosalo.androidreview.activities.main.MainComponent;
import co.gosalo.androidreview.activities.main.MainModule;
import co.gosalo.androidreview.activities.selectedtickets.SelectedTicketsComponent;
import co.gosalo.androidreview.activities.selectedtickets.SelectedTicketsModule;
import co.gosalo.androidreview.app.api.GosaloService;
import co.gosalo.androidreview.app.api.NetworkModule;
import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    //Context context();

    GosaloService gosaloService();

    Navigator navigator();

    //SubComponents

    MainComponent plusMainComponent(MainModule mainModule);

    EventDetailComponent plusEventDetailComponent(EventDetailModule eventDetailModule);

    SelectedTicketsComponent plusSelectedTicketsComponent(SelectedTicketsModule selectedTicketsModule);


}
