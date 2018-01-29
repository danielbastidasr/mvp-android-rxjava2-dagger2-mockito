package co.gosalo.androidreview.view.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.gosalo.androidreview.R;
import co.gosalo.androidreview.api.model.Event;
import co.gosalo.androidreview.view.holder.EventViewHolder;



public class EventAdapter  extends RecyclerView.Adapter<EventViewHolder>{

    private List<Event> events;

    public EventAdapter(List<Event> events ) {
        this.events = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eventCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event,parent,false);
        return new EventViewHolder(eventCard);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        final Event event = events.get(position);
        holder.updateUI(event);
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

