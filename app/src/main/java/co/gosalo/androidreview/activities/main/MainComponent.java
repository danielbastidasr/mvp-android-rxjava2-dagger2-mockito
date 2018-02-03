package co.gosalo.androidreview.activities.main;


import co.gosalo.androidreview.app.AppComponent;
import dagger.Component;



@MainScope
@Component(modules = { MainModule.class }, dependencies = AppComponent.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

}