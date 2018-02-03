package co.gosalo.androidreview.activities.main.mvp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.gosalo.androidreview.R;
import co.gosalo.androidreview.app.api.model.Event;


public class EventViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTextView;
    private TextView venueTextView;

    public EventViewHolder(View itemView) {
        super(itemView);
        this.nameTextView = itemView.findViewById(R.id.card_event_name);
        this.venueTextView = itemView.findViewById(R.id.card_event_venue);
    }

    public void updateUI(Event event){
        this.nameTextView.setText(event.getTitle());
        this.venueTextView.setText(event.getVenue());
    }

}
