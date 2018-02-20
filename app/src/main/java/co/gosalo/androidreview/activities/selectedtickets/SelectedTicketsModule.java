package co.gosalo.androidreview.activities.selectedtickets;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;


@Module
public class SelectedTicketsModule {

    private final Activity activity;
    private final String tickets;

    public SelectedTicketsModule(Activity activity, String tickets) {
        this.activity = activity;
        this.tickets = tickets;
    }

    @Provides
    @SelectedTicketsScope
    public SelectedTicketsView view(){
        return new SelectedTicketsView(activity,tickets);
    }

}