package co.gosalo.androidreview;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
}
