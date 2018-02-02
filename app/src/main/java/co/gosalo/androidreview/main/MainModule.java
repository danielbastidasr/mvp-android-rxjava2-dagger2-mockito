package co.gosalo.androidreview.main;

import android.app.Activity;

import co.gosalo.androidreview.app.api.GosaloService;
import co.gosalo.androidreview.main.mvp.MainModel;
import co.gosalo.androidreview.main.mvp.MainPresenter;
import co.gosalo.androidreview.main.mvp.view.MainActivityView;
import dagger.Module;
import dagger.Provides;


@Module
public class MainModule {
    private final Activity activity;

    public MainModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @MainScope
    public MainActivityView view(){
        return new MainActivityView(activity);
    }

    @Provides
    @MainScope
    public MainModel model(GosaloService gosaloService){
        return new MainModel(activity,gosaloService);
    }

    @Provides
    @MainScope
    public MainPresenter mainPresenter(MainActivityView view, MainModel model){
        return new MainPresenter(view,model);
    }


}
