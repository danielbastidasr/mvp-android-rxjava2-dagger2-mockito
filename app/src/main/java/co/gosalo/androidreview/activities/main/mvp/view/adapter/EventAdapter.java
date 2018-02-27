package co.gosalo.androidreview.activities.main.mvp.view.adapter;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.gosalo.androidreview.R;
import co.gosalo.androidreview.activities.main.MainActivity;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.activities.main.mvp.view.holder.EventViewHolder;



public class EventAdapter  extends RecyclerView.Adapter<EventViewHolder>{

    private List<Event> events;
    private MainActivity activity;

    public EventAdapter(List<Event> events, MainActivity activity) {
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

        /**INCREMENT LIST WILL FIRE THE API CALL**/

        if (position == getItemCount() - 1) {
            activity.incrementEventsList();
        }

        final Event event = events.get(position);
        holder.updateUI(event);

        holder.itemView.setOnClickListener(
            view ->activity.startDetailActivity(event)
        );
    }

    @Override
    public int getItemCount() {
        if( this.events == null )
            return 0;
        return this.events.size();
    }


    public void setEvents(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }



    private void remove(int swipedPosition) {
        if(swipedPosition<getItemCount()){
            events.remove(swipedPosition);
            notifyItemRemoved(swipedPosition);
            notifyItemRangeChanged(swipedPosition, events.size());
        }
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

    // DELETE ROW (EVENT ITEM) BY DRAGGING TO RIGHT

    public static class DeleteItemByDragToRight extends ItemTouchHelper.SimpleCallback{


        public DeleteItemByDragToRight() {
            super(0, ItemTouchHelper.RIGHT);
        }

        // not important, we don't want drag & drop
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }


        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int swipedPosition = viewHolder.getAdapterPosition();
            EventAdapter adapter = (EventAdapter) ((RecyclerView)viewHolder.itemView.getParent()).getAdapter();
            adapter.remove(swipedPosition);
        }

    }




}

