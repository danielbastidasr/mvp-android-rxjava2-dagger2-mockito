package co.gosalo.androidreview.activities.main.mvp.view.adapter;

import android.app.Activity;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.gosalo.androidreview.R;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.activities.eventdetail.EventDetailActivity;
import co.gosalo.androidreview.activities.main.mvp.view.holder.EventViewHolder;



public class EventAdapter  extends RecyclerView.Adapter<EventViewHolder>{

    private List<Event> events;

    private Activity activity;

    public EventAdapter(List<Event> events, Activity activity) {
        this.events = events;
        this.activity = activity;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventCard = LayoutInflater.from(activity).inflate(R.layout.card_event,parent,false);

        return new EventViewHolder(eventCard);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        final Event event = events.get(position);
        holder.updateUI(event);

        holder.itemView.setOnClickListener(
            view -> EventDetailActivity.start(activity,event)
        );
    }

    @Override
    public int getItemCount() {
        if( this.events == null )
            return 0;
        return this.events.size();
    }

    // JUST SEPARATION AMONG THE EVENTS
    public static class SpaceItems extends RecyclerView.ItemDecoration{

        private final int spacer;

        public SpaceItems(int spacer) {
            this.spacer = spacer;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = spacer;
        }
    }
}

