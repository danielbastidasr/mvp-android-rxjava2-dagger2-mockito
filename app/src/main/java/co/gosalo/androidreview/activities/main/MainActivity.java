package co.gosalo.androidreview.activities.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import javax.inject.Inject;

import co.gosalo.androidreview.activities.eventdetail.EventDetailActivity;
import co.gosalo.androidreview.app.GosaloApp;
import co.gosalo.androidreview.app.api.GosaloService;
import co.gosalo.androidreview.activities.main.mvp.MainPresenter;
import co.gosalo.androidreview.activities.main.mvp.view.MainActivityView;
import co.gosalo.androidreview.app.api.model.Event;


public class MainActivity extends AppCompatActivity {

    @Inject
    GosaloService service;


    @Inject
    MainActivityView activityView;

    @Inject
    MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DaggerMainComponent.builder()
                .appComponent(GosaloApp.get(this).component())
                .mainModule(new MainModule(this))
                .build().inject(this);

        setContentView(activityView);

        presenter.onCreate();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    public void startDetailActivity(Event event){
        EventDetailActivity.start(this,event);
    }
}
