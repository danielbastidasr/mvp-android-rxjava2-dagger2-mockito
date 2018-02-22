package co.gosalo.androidreview.activities.selectedtickets;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import co.gosalo.androidreview.activities.selectedtickets.mvp.SelectedTicketsPresenter;
import co.gosalo.androidreview.activities.selectedtickets.mvp.SelectedTicketsView;
import co.gosalo.androidreview.app.GosaloApp;

public class SelectedTicketsActivity extends AppCompatActivity {

    public static final String INTENT_DATA = "tickets";
    private static String tickets;


    @Inject
    SelectedTicketsView activityView;

    @Inject
    SelectedTicketsPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tickets = this.getIntent().getStringExtra(INTENT_DATA);

        GosaloApp.createSelectedTicketsComponent(this,tickets).inject(this);

        setContentView(activityView);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestoy();

    }


    public static void start(Context context, String tickets) {

        Intent intent = new Intent(context, SelectedTicketsActivity.class);
        intent.putExtra(INTENT_DATA,tickets);
        context.startActivity(intent);
    }

}
