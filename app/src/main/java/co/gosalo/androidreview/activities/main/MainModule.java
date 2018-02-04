package co.gosalo.androidreview.activities.main;


import co.gosalo.androidreview.app.api.GosaloService;
import co.gosalo.androidreview.activities.main.mvp.MainModel;
import co.gosalo.androidreview.activities.main.mvp.MainPresenter;
import co.gosalo.androidreview.activities.main.mvp.view.MainActivityView;
import dagger.Module;
import dagger.Provides;


@Module
public class MainModule {
    private final MainActivity activity;

    public MainModule(MainActivity activity) {
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
