package co.gosalo.androidreview.activities.eventdetail.mvp;


import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class EventDetailPresenter {
    private final EventDetailView view;
    private final EventDetailModel model;

    private final CompositeDisposable disposables = new CompositeDisposable();


    public EventDetailPresenter(EventDetailView view, EventDetailModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate(){

        disposables.add(getEvent());

        disposables.add(observeOnButtonAndSelection());
    }



    public void onDestroy(){

        disposables.clear();

    }



    private Disposable getEvent(){
        return model.getEvent().subscribe(
                event -> view.setEvent(event)
        );
    }

    private Disposable observeOnButtonAndSelection(){
        return view.observeOnTicketPick()
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
}
