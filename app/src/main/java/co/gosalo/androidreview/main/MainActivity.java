package co.gosalo.androidreview.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import javax.inject.Inject;

import co.gosalo.androidreview.app.GosaloApp;
import co.gosalo.androidreview.app.api.GosaloService;
import co.gosalo.androidreview.main.mvp.MainPresenter;
import co.gosalo.androidreview.main.mvp.view.MainActivityView;


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
}
