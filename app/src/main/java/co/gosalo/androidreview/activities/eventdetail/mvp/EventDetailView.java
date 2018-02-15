package co.gosalo.androidreview.activities.eventdetail.mvp;

import android.app.Activity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.gosalo.androidreview.R;
import co.gosalo.androidreview.app.api.model.Event;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;



public class EventDetailView extends FrameLayout  {

    private Activity activity;

    @BindView(R.id.event_detail_name)
    TextView eventName;

    @BindView(R.id.event_detail_venue)
    TextView eventVenue;

    @BindView(R.id.event_detail_tickets_picker)
    NumberPicker ticketNumber;

    @BindView(R.id.event_detail_buy_button)
    Button buyButton;



    public EventDetailView(Activity activity) {
        super(activity);

        this.activity = activity;

        inflate(this.activity, R.layout.activity_event_detail,this);
        ButterKnife.bind(this);

    }

    public void setEvent(Event event){
        this.eventName.setText(event.getTitle());
        this.eventVenue.setText(event.getVenue());
    }


    public Observable<Integer> observeOnTicketPick() {

        PublishSubject<Integer> picker = PublishSubject.create();

        ticketNumber.setMaxValue(10);
        ticketNumber.setMinValue(0);
        ticketNumber.setOnValueChangedListener((numberPicker, from, to) -> picker.onNext(to));

        return picker;
    }

    public Observable<Object> observeButton() {
        return  RxView.clicks(buyButton);
    }

}
