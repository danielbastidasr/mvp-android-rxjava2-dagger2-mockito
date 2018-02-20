package co.gosalo.androidreview.activities.eventdetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import co.gosalo.androidreview.activities.eventdetail.mvp.EventDetailPresenter;
import co.gosalo.androidreview.app.GosaloApp;
import co.gosalo.androidreview.app.Navigator;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.activities.eventdetail.mvp.EventDetailView;

public class EventDetailActivity extends AppCompatActivity {

    public static final String INTENT_DATA = "event";
    private static Event event;

    @Inject
    Navigator navigator;

    @Inject
    EventDetailView activityView;

    @Inject
    EventDetailPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        event = this.getIntent().getParcelableExtra(EventDetailActivity.INTENT_DATA);

        DaggerEventDetailComponent.builder()
                .appComponent(GosaloApp.get(this).component())
                .eventDetailModule(new EventDetailModule(this,event))
                .build().inject(this);

        setContentView(activityView);

        presenter.onCreate();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }



    public static void start(Context context, Event event) {

        Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra(INTENT_DATA, event);
        context.startActivity(intent);
    }

    public void startSelectedTicketsActivity(String ticketSelected){
        navigator.navigateToEventDetailPicked(this,ticketSelected);
    }
}
