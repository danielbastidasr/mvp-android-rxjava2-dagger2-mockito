package co.gosalo.androidreview.activities.eventdetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import co.gosalo.androidreview.activities.eventdetail.mvp.EventDetailPresenter;
import co.gosalo.androidreview.app.GosaloApp;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.activities.eventdetail.mvp.EventDetailView;

public class EventDetailActivity extends AppCompatActivity {

    public static final String INTENT_DATA = "event";

    @Inject
    EventDetailView activityView;

    @Inject
    EventDetailPresenter presenter;


    public static void start(Context context, Event event) {

        Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra(INTENT_DATA, event);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerEventDetailComponent.builder()
                .appComponent(GosaloApp.get(this).component())
                .eventDetailModule(new EventDetailModule(this))
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
