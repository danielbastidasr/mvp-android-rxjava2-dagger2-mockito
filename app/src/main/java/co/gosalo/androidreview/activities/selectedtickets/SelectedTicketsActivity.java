package co.gosalo.androidreview.activities.selectedtickets;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import co.gosalo.androidreview.app.GosaloApp;

public class SelectedTicketsActivity extends AppCompatActivity {

    public static final String INTENT_DATA = "tickets";
    private static String tickets;


    @Inject
    SelectedTicketsView activityView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tickets = this.getIntent().getStringExtra(INTENT_DATA);

        DaggerSelectedTicketsComponent.builder()
                .appComponent(GosaloApp.get(this).component())
                .selectedTicketsModule(new SelectedTicketsModule(this,tickets))
                .build().inject(this);


        setContentView(activityView);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public static void start(Context context, String tickets) {

        Intent intent = new Intent(context, SelectedTicketsActivity.class);
        intent.putExtra(INTENT_DATA,tickets);
        context.startActivity(intent);
    }

}
