package co.gosalo.androidreview;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import co.gosalo.androidreview.activities.main.mvp.MainModel;
import co.gosalo.androidreview.activities.main.mvp.MainPresenter;
import co.gosalo.androidreview.activities.main.mvp.view.MainActivityView;
import co.gosalo.androidreview.app.api.PagedResponseBody;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.utilities.RxSchedulersOverrideRule;
import io.reactivex.Observable;


public class MainPresenterTest {

    private MainPresenter mainPresenter;
    private MainActivityView mainView;
    private MainModel mainModel;

    private PagedResponseBody<List<Event>> mockEvents = new PagedResponseBody<>();

    @Rule
    public RxSchedulersOverrideRule rule = new RxSchedulersOverrideRule();


    @Before
    public void setUp()throws Exception {


        mainView = Mockito.mock(MainActivityView.class);
        mainModel = Mockito.mock(MainModel.class);

        mainPresenter = new MainPresenter(mainView,mainModel);


    }

    @Test
    public void onGetEventsNoData(){
        Mockito.when(mainModel.getListEvents()).thenReturn(Observable.just(mockEvents));


        mainPresenter.onCreate();
        Mockito.verify(mainView,Mockito.times(1)).showLoading(false);
        mainPresenter.onDestroy();
    }


}
