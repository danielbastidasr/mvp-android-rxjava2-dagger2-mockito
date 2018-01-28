package co.gosalo.androidreview;

import javax.inject.Singleton;

import co.gosalo.androidreview.api.GosaloClient;
import co.gosalo.androidreview.api.GosaloService;
import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    @Singleton
    @Provides
    public GosaloService provideGosaloService() {
        return new GosaloClient().getGosaloService();
    }
}
