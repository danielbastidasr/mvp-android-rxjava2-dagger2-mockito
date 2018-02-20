package co.gosalo.androidreview.activities.selectedtickets;


import android.app.Activity;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.gosalo.androidreview.R;

public class SelectedTicketsView extends FrameLayout {

    private Activity activity;
    private String  nameSelected;

    @BindView(R.id.tickets_selected_tv)
    TextView ticketsSelected;


    public SelectedTicketsView(Activity activity, String ticketSelected) {
        super(activity);

        this.activity = activity;
        this.nameSelected = ticketSelected;

        inflate(this.activity, R.layout.activity_selected_tickets,this);
        ButterKnife.bind(this);

        setTicketsSelected(this.nameSelected);

    }

    public void setTicketsSelected(String ticketsSelected){
        this.ticketsSelected.setText(ticketsSelected);
    }

}
