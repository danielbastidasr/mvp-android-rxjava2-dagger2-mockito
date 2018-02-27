package co.gosalo.androidreview.activities.main.mvp;


import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import co.gosalo.androidreview.activities.main.MainActivity;
import co.gosalo.androidreview.app.api.GosaloService;
import co.gosalo.androidreview.app.api.PagedResponseBody;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.utilities.ReactiveSaveState;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;


public class MainModel {

    private static String bundleNameList = "LISTSAVED";
    private static String bundleNamePage = "PAGESAVED";
    private static String bundleNameLast = "PAGEISLAST";

    //ActualState

    private List<Event> events = new ArrayList<>();
    private boolean last = false;
    private int page = 0;



    private final GosaloService gosaloService;
    private final MainActivity activity;
    private ReactiveSaveState reactiveSaveState = new ReactiveSaveState();



    public MainModel(GosaloService gosaloService, MainActivity activity) {
        this.gosaloService = gosaloService;
        this.activity = activity;

    }

    public Single<List<Event>> getEventsFromSaveStateOrApi() {
        return getEventsFromSaveState()
                .isEmpty().flatMap(
                isEmpty->
                {

                    if (isEmpty){
                        //IS EMPTY SO WE NEED TO DO API CALL
                        return getEvents(0).single(events).map(
                                list -> {
                                    events = list;
                                    return list;
                                }
                        );
                    }

                    else {
                        //IS NOT EMPTY: RETRIEVE EVENTS FROM SAVED STATE
                        return getEventsFromSaveState().toSingle(events).map(
                                list -> {
                                    events = list;
                                    return list;
                                }
                        );
                    }

                }
        );
    }


    public Observable<List<Event>> getEvents(int page){

        return getListEvents(page).map(
            eventsPage -> {
                last = eventsPage.isLast();
                this.page = page;
                events.addAll(eventsPage.getContent());
                return events;
            }

        );
    }


    private Observable<PagedResponseBody<List<Event>>> getListEvents(Integer page){
         return gosaloService.getEvents(page);
    }

    public void saveEventsState(List<Event> list) {
        reactiveSaveState.updateSaveState(activity, bundle -> {
            bundle.putParcelableArrayList(bundleNameList, (ArrayList<? extends Parcelable>) list);
            bundle.putInt(bundleNamePage,page);
            bundle.putBoolean(bundleNameLast,last);
        });
    }

    public Maybe<List<Event>> getEventsFromSaveState() {
        return reactiveSaveState.getSavedState(activity).map(
                bundle -> {
                    last = bundle.getBoolean(bundleNameLast);
                    page = bundle.getInt(bundleNamePage);
                    return bundle.getParcelableArrayList(bundleNameList);
                }
        );
    }


    public boolean isLast() {
        return last;
    }

    public int getPage() {
        return page;
    }

}
