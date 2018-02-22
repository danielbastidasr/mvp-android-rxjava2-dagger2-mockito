package co.gosalo.androidreview.activities.main;


import co.gosalo.androidreview.app.AppComponent;
import co.gosalo.androidreview.app.AppModule;
import dagger.Component;
import dagger.Subcomponent;




@MainScope
@Subcomponent(modules = { MainModule.class} )
public interface MainComponent {

    void inject(MainActivity mainActivity);

}