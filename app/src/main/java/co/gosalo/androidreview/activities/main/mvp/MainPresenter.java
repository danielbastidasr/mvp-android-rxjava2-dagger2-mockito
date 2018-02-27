package co.gosalo.androidreview.activities.main.mvp;


import co.gosalo.androidreview.activities.main.mvp.view.MainActivityView;


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
        //Initialise the observable change page
        changePage.onNext(model.getPage());
        //Initialise retrieve more Events
        disposableBag.add(retrieveEventsWhenPageChanged());
        //SetUp Initial State
        disposableBag.add(getEventsFromSaveStateOrApi());

    }
    /**ON DESTROY ACTIVITY WILL DISPOSE ALL THE SUBSCRIPTIONS**/
    public void onDestroy(){
        disposableBag.dispose();
    }

    /**USER WILL CALL THIS METHOD ASKING FOR MORE EVENTS**/
    public void incrementPage(){
        if(!model.isLast()){
            changePage.onNext(model.getPage()+1);
        }

    }

    /**SUBSCRIPTION TO GET EVENTS FROM API WHEN USER CHANGED PAGE**/
    private Disposable retrieveEventsWhenPageChanged(){
        return changePage.subscribe(
                    page->{
                        view.showLoading(true);
                        disposableBag.add(getEventsFromApi(page));
                    }
                );

    }


    /**SUBSCRIPTION TO GET EVENTS FROM API GIVEN A PAGE**/
    private Disposable getEventsFromApi(int page){
        return model.getEvents(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(events->{
                    view.showLoading(true);
                    model.saveEventsState(events);

                })
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


    /**SUBSCRIPTION TO GET EVENTS FROM API OR SAVED STATE**/
    private Disposable getEventsFromSaveStateOrApi(){
        return model.getEventsFromSaveStateOrApi()
                .map(
                        events -> {
                            view.showLoading(true);
                            return events;
                        }
                )
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
