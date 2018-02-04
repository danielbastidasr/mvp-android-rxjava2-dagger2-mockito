package co.gosalo.androidreview.activities.eventdetail.mvp;


import io.reactivex.disposables.Disposable;

public class EventDetailPresenter {
    private final EventDetailView view;
    private final EventDetailModel model;

    private Disposable disposable;

    public EventDetailPresenter(EventDetailView view, EventDetailModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate(){
        disposable = model.getEvent().subscribe(
                event -> {
                    view.setEventName(event.getTitle());
                }
        );
    }

    public void onDestroy(){
        disposable.dispose();
    }
}
