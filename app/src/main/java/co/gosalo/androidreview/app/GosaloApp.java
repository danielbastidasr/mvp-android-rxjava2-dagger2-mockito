package co.gosalo.androidreview.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;


public class GosaloApp extends Application {

    private static AppComponent appComponent;


    public static GosaloApp get(Activity activity) {
       return (GosaloApp) activity.getApplication();
    }

    public static GosaloApp get(Service service) {
       return (GosaloApp) service.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent component() {
        return appComponent;
    }
}
