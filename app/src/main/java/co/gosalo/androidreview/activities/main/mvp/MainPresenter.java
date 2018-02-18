package co.gosalo.androidreview.activities.main.mvp;

import android.util.Log;

import co.gosalo.androidreview.activities.main.mvp.view.MainActivityView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;


public class MainPresenter {
    private final MainActivityView view;
    private final MainModel model;


    private Disposable disposable;

    public MainPresenter(MainActivityView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate(){
        view.showLoading(true);


        disposable = model.getListEvents()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        listPagedResponseBody -> view.setUpRecyclerView(listPagedResponseBody.getContent())
                        ,
                        err -> {
                            HttpException exception = (HttpException) err;

                            view.emptyList("Couldn't load the events: "+ exception.code()+exception.message());
                            view.showLoading(false);

                        }
                        ,
                        ()-> {
                            view.showLoading(false);
                            Log.d("SUBSCRIPTION", "onCompleted: ");
                        }
                );

    }

    public void onDestroy(){

        disposable.dispose();
    }


}
