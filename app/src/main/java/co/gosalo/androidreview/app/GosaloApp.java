package co.gosalo.androidreview.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;


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

    public static AppComponent component() {
        return appComponent;
    }
}
