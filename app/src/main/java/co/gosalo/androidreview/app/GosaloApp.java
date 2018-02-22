package co.gosalo.androidreview.app;

import android.app.Activity;
import android.app.Application;

import co.gosalo.androidreview.activities.eventdetail.EventDetailActivity;
import co.gosalo.androidreview.activities.eventdetail.EventDetailComponent;
import co.gosalo.androidreview.activities.eventdetail.EventDetailModule;
import co.gosalo.androidreview.activities.main.MainActivity;
import co.gosalo.androidreview.activities.main.MainComponent;
import co.gosalo.androidreview.activities.main.MainModule;
import co.gosalo.androidreview.activities.selectedtickets.SelectedTicketsActivity;
import co.gosalo.androidreview.activities.selectedtickets.SelectedTicketsComponent;
import co.gosalo.androidreview.activities.selectedtickets.SelectedTicketsModule;
import co.gosalo.androidreview.app.api.model.Event;


public class GosaloApp extends Application {

    private static AppComponent appComponent;
    private static Navigator navigator;


    public static GosaloApp get(Activity activity) {
       return (GosaloApp) activity.getApplication();
    }

    public static Navigator getNavigator(){
        return navigator;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        navigator = new Navigator();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }


    //SubComponents

    public static MainComponent createMainComponent (MainActivity mainActivity){

        return appComponent.plusMainComponent(new MainModule(mainActivity));

    }

    public static EventDetailComponent createEventDetailComponent (EventDetailActivity activity, Event event){

        return appComponent.plusEventDetailComponent(new EventDetailModule(activity,event));

    }

    public static SelectedTicketsComponent createSelectedTicketsComponent(SelectedTicketsActivity activity, String tickets){

        return appComponent.plusSelectedTicketsComponent(new SelectedTicketsModule(activity,tickets));

    }


}
