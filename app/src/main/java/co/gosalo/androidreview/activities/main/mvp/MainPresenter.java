package co.gosalo.androidreview.activities.main.mvp;



import java.util.List;

import co.gosalo.androidreview.activities.main.mvp.view.MainActivityView;


import co.gosalo.androidreview.app.api.model.Event;
import io.reactivex.Observable;
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
    private CompositeDisposable disposableBag = new CompositeDisposable();

    public MainPresenter(MainActivityView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate(){
        //Initialize the observable
        setPage(model.getPage());
        //Initialize retrieve Events from the API
        disposableBag.add(retrieveEvents());
        //SetUp Initial State either with SavedState in the Fragment or API Call with the page 0
        disposableBag.add(getEventsFromSaveStateOrApi());


    }



    public void onDestroy(){
        disposableBag.dispose();
    }

    public void setPage(Integer page) {
        changePage.onNext(page);
    }

    public void incrementPage(){
        if(!model.isLast()){
            changePage.onNext(model.getPage()+1);
        }

    }

    private Disposable retrieveEvents(){
        return changePage.subscribe(
                    page->{
                        view.showLoading(true);
                        disposableBag.add(getEventsFromApi(page));
                    }
                );

    }

    private Observable<List<Event>> retrieveEventsPage(int page){
        return  model.getEvents(page);

    }

    private Disposable getEventsFromApi(int page){
        return retrieveEventsPage(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(events->model.saveEventsState(events))
                .subscribe(
                        events -> view.setUpRecyclerView(events)
                        ,
                        err ->{
                            /**TODO:Need to pass to the view the different error cases to be displayed in the View**/
                            if(err instanceof HttpException){
                                HttpException exception = (HttpException) err;
                                view.emptyList();
                            }

                            else view.emptyList();
                            view.showLoading(false);
                        }
                        , ()->view.showLoading(false)
                );

    }


    private Disposable getEventsFromSaveStateOrApi(){
        return model.getEventsFromSaveStateOrApi()
                .toObservable()
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        events ->{
                            model.saveEventsState(events);
                            view.setUpRecyclerView(events);
                            view.showLoading(false);
                        }
                        ,
                        err ->{
                            /**TODO:Need to pass to the view the different error cases to be displayed in the View**/
                            if(err instanceof HttpException){
                                HttpException exception = (HttpException) err;
                                view.emptyList();
                            }

                            else view.emptyList();
                            view.showLoading(false);
                        }
                );
    }

}
