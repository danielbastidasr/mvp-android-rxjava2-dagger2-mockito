package co.gosalo.androidreview.activities.eventdetail.mvp;

import android.app.Activity;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.gosalo.androidreview.R;
import co.gosalo.androidreview.app.api.model.Event;


public class EventDetailView extends FrameLayout {
    private Activity activity;

    @BindView(R.id.event_detail_name)
    TextView eventName;

    @BindView(R.id.event_detail_venue)
    TextView eventVenue;

    public EventDetailView(Activity activity) {
        super(activity);

        this.activity = activity;

        inflate(this.activity, R.layout.activity_event_detail,this);
        ButterKnife.bind(this);

    }

    void setEvent(Event event){
        this.eventName.setText(event.getTitle());
        this.eventVenue.setText(event.getVenue());
    }



}
