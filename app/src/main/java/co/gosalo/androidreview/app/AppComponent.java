package co.gosalo.androidreview.app;

import android.content.Context;


import co.gosalo.androidreview.app.api.GosaloService;
import co.gosalo.androidreview.app.api.NetworkModule;
import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    Context context();

    GosaloService gosaloService();

}
