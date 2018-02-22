package co.gosalo.androidreview.activities.main.mvp;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.gosalo.androidreview.activities.main.mvp.view.MainActivityView;


import co.gosalo.androidreview.app.api.model.Event;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.HttpException;


public class MainPresenter {
    private final MainActivityView view;
    private final MainModel model;


    private PublishSubject<Integer> changePage = PublishSubject.create();
    private int currentPage = 0;
    private List<Event> events = new ArrayList<>();
    private boolean last = false;


    private CompositeDisposable disposableBag = new CompositeDisposable();

    public MainPresenter(MainActivityView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate(){

        view.setUpRecyclerView(events);
        disposableBag.add(retrieveEventsWhenPageChange());
        setPage(0);


    }

    public void onDestroy(){
        disposableBag.dispose();
    }



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


    private Disposable retrieveEventsWhenPageChange() {

        return changePage.subscribe(
            (pageNumber) -> {
                /** IF CURRENT PAGE IS NOT THE LAST ONE **/
                if(!last){
                    view.showLoading(true);
                    disposableBag.add(retrieveEvents(pageNumber));
                }

            });
    }

    private Disposable retrieveEvents(int pageNumber){
        return model.getListEvents(pageNumber)
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
                            /**TODO:Need to pass to the view the different error cases to be displayed in the View**/
                            if(err instanceof HttpException){
                                HttpException exception = (HttpException) err;
                                view.emptyList();
                            }

                            else view.emptyList();
                            view.showLoading(false);
                        }
                        ,
                        ()-> view.showLoading(false)
                );
    }

}
