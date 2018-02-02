package co.gosalo.androidreview.main.mvp;

import android.util.Log;

import co.gosalo.androidreview.main.mvp.view.MainActivityView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



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
                    listPagedResponseBody -> {
                        view.showLoading(false);
                        view.setUpRecyclerView(listPagedResponseBody.getContent());

                    },
                    err ->{
                        view.showLoading(false);
                        Log.d("ERROR", "There was an error with getEvents on subscribe ");
                    }
                );



    }

    public void onDestroy(){

        disposable.dispose();
    }


}
