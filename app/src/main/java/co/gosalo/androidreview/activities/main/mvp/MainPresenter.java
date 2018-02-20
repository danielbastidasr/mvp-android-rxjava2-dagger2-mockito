package co.gosalo.androidreview.activities.main.mvp;


import java.util.ArrayList;
import java.util.List;

import co.gosalo.androidreview.activities.main.mvp.view.MainActivityView;


import co.gosalo.androidreview.app.api.model.Event;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.HttpException;


public class MainPresenter {
    private final MainActivityView view;
    private final MainModel model;

    /** LOAD NEXT PAGE STATE **/
        private PublishSubject<Integer> changePage = PublishSubject.create();
        private int currentPage = 0;


        private List<Event> events = new ArrayList<>();
        private boolean last = false;


        public void setPage(Integer page) {
            currentPage = page;
            changePage.onNext(page);
        }

        public void incrementPage(){
            if(!last){
                currentPage++;
                changePage.onNext(currentPage);
            }

        }
    /**END NEXT PAGE STATE**/

    private Disposable disposable, disposableEvents;

    public MainPresenter(MainActivityView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate(){

        view.setUpRecyclerView(events);
        disposable = retrieveEvents();
        setPage(0);


    }

    public void onDestroy(){
        disposable.dispose();
        if (disposableEvents != null) {
            disposableEvents.dispose();
        }
    }


    public Disposable retrieveEvents() {

        return changePage.subscribe(
                (pageNumber) -> {
                    /** IF CURRENT PAGE IS NOT THE LAST ONE **/
                    if(!last){

                        view.showLoading(true);

                        disposableEvents = model.getListEvents(pageNumber)
                                .take(1)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(
                                        listPagedResponseBody -> {
                                            last = listPagedResponseBody.isLast();
                                            events.addAll(listPagedResponseBody.getContent());
                                            view.setUpRecyclerView(events);

                                        }
                                        ,
                                        err -> {
                                            if(err instanceof HttpException){
                                                HttpException exception = (HttpException) err;
                                                view.emptyList("Couldn't load the events: "+ exception.code()+exception.message());

                                            }
                                            else view.emptyList("Couldn't load the events: "+ err.getCause());
                                            view.showLoading(false);

                                        }
                                        ,
                                        ()-> view.showLoading(false)

                                );
                    }

                });
    }


}
