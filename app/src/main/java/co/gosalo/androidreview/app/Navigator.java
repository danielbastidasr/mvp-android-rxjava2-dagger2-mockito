package co.gosalo.androidreview.app;


import android.content.Context;

import co.gosalo.androidreview.activities.eventdetail.EventDetailActivity;
import co.gosalo.androidreview.activities.selectedtickets.SelectedTicketsActivity;
import co.gosalo.androidreview.app.api.model.Event;

public class Navigator {
    public Navigator(){}

    public void navigateToEventDetail(Context context, Event event){
        if(context!=null){
            EventDetailActivity.start(context,event);
        }
    }

    public void navigateToEventDetailPicked(Context context, String event){
        if(context != null ){
            SelectedTicketsActivity.start(context,event);
        }
    }

}
