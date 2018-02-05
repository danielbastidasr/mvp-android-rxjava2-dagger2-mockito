package co.gosalo.androidreview.activities.eventdetail.mvp;


import android.util.Log;

import io.reactivex.disposables.Disposable;

public class EventDetailPresenter {
    private final EventDetailView view;
    private final EventDetailModel model;

    private Disposable disposable, disposable2;

    public EventDetailPresenter(EventDetailView view, EventDetailModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate(){

        disposable = model.getEvent().subscribe(
                event -> view.setEvent(event)
        );

        disposable2 = view.observeOnTicketPick()
                .doOnNext(tickets -> {

                    view.buyButton.setClickable(true);
                    Log.d("Picked", "Picked: "+tickets);

                })
                .switchMap(
                    ticketNumber -> view.observeButton().map(
                            __-> {
                                return ticketNumber;
                            }
                    )
                )
                .subscribe(
                    ticketsNumber-> Log.d("Clicked", "Picked and Clicked: "+ticketsNumber)
                );

    }


    public void onDestroy(){
        disposable.dispose();
        disposable2.dispose();

    }
}
