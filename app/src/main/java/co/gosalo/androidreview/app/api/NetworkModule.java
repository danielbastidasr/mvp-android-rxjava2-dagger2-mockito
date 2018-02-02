package co.gosalo.androidreview.app.api;


import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {


    @Provides
    public GosaloService provideGosaloService() {
        return new GosaloClient().getGosaloService();
    }
}
