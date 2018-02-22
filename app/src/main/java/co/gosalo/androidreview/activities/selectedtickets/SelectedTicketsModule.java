package co.gosalo.androidreview.activities.selectedtickets;

import android.app.Activity;

import co.gosalo.androidreview.activities.selectedtickets.mvp.SelectedTicketsModel;
import co.gosalo.androidreview.activities.selectedtickets.mvp.SelectedTicketsPresenter;
import co.gosalo.androidreview.activities.selectedtickets.mvp.SelectedTicketsView;
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
        return new SelectedTicketsView(activity);
    }


    @Provides
    @SelectedTicketsScope
    public SelectedTicketsModel model(){
        return new SelectedTicketsModel(tickets);
    }

    @Provides
    @SelectedTicketsScope
    public SelectedTicketsPresenter presenter(SelectedTicketsView view, SelectedTicketsModel model){
        return new SelectedTicketsPresenter(view,model);
    }

}